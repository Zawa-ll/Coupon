package com.example.coupon;

import com.example.coupon.annotation.IgnorePermission;
import com.example.coupon.annotation.CouponPermission;
import com.example.coupon.vo.PermissionInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
public class AnnotationScanner {

    private String pathPrefix;

    private static final String COUPON_PKG = "com.example.coupon";

    AnnotationScanner(String prefix) {
        this.pathPrefix = trimPath(prefix);
    }

    /**
     * Constructs permission information for all Controllers
     * */
    List<PermissionInfo> scanPermission(
            Map<RequestMappingInfo, HandlerMethod> mappingMap
    ) {

        List<PermissionInfo> result = new ArrayList<>();
        mappingMap.forEach((mapInfo, method) ->
                result.addAll(buildPermission(mapInfo, method)));

        return result;
    }

    private List<PermissionInfo> buildPermission(
            RequestMappingInfo mapInfo, HandlerMethod handlerMethod
    ) {

        Method javaMethod = handlerMethod.getMethod();
        Class baseClass = javaMethod.getDeclaringClass();

        // Ignore mappings that are not under com.example.coupon
        if (!isCouponPackage(baseClass.getName())) {
            log.debug("ignore method: {}", javaMethod.getName());
            return Collections.emptyList();
        }

        // Determine if this method needs to be ignored
        IgnorePermission ignorePermission = javaMethod.getAnnotation(
                IgnorePermission.class
        );
        if (null != ignorePermission) {
            log.debug("ignore method: {}", javaMethod.getName());
            return Collections.emptyList();
        }

        // Check out the permissions annotation
        CouponPermission couponPermission = javaMethod.getAnnotation(
                CouponPermission.class
        );
        if (null == couponPermission) {
            // If there is no CouponPermission and there is no IgnorePermission, log it in the logs.
            log.error("lack @CouponPermission -> {}#{}",
                    javaMethod.getDeclaringClass().getName(),
                    javaMethod.getName());
            return Collections.emptyList();
        }

        // Fetch the URL
        Set<String> urlSet = mapInfo.getPatternsCondition().getPatterns();

        // takeout method
        boolean isAllMethods = false;
        Set<RequestMethod> methodSet = mapInfo.getMethodsCondition().getMethods();
        if (CollectionUtils.isEmpty(methodSet)) {
            isAllMethods = true;
        }

        List<PermissionInfo> infoList = new ArrayList<>();

        for (String url : urlSet) {

            // Supported http method is full
            if (isAllMethods) {
                PermissionInfo info = buildPermissionInfo(
                        HttpMethodEnum.ALL.name(),
                        javaMethod.getName(),
                        this.pathPrefix + url,
                        couponPermission.readOnly(),
                        couponPermission.description(),
                        couponPermission.extra()
                );
                infoList.add(info);
                continue;
            }

            // Support for some http methods
            for (RequestMethod method : methodSet) {
                PermissionInfo info = buildPermissionInfo(
                        method.name(),
                        javaMethod.getName(),
                        this.pathPrefix + url,
                        couponPermission.readOnly(),
                        couponPermission.description(),
                        couponPermission.extra()
                );
                infoList.add(info);
                log.info("permission detected: {}", info);
            }
        }

        return infoList;
    }

    /**
     * Constructing permission information for a single interface
     * */
    private PermissionInfo buildPermissionInfo(
            String reqMethod, String javaMethod, String path,
            boolean readOnly, String desp, String extra
    ) {

        PermissionInfo info = new PermissionInfo();
        info.setMethod(reqMethod);
        info.setUrl(path);
        info.setIsRead(readOnly);
        info.setDescription(
                // If not described in the annotation, use the method name.
                StringUtils.isEmpty(desp) ? javaMethod : desp
        );
        info.setExtra(extra);

        return info;
    }

    /**
     * Determine if the current class is in the package we defined
     * */
    private boolean isCouponPackage(String className) {

        return className.startsWith(COUPON_PKG);
    }

    /**
     * Ensures that path starts with / and does not end with /.
     * if user -> /user, /user/ -> /user
     * */
    private String trimPath(String path) {

        if (StringUtils.isEmpty(path)) {
            return "";
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }

        return path;
    }
}
