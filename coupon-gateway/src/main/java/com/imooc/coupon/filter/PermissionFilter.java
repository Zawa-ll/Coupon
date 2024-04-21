package com.example.coupon.filter;

import com.alibaba.fastjson.JSON;
import com.example.coupon.permission.PermissionClient;
import com.example.coupon.vo.CheckPermissionRequest;
import com.example.coupon.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Component
public class PermissionFilter extends AbsSecurityFilter {

    private final PermissionClient permissionClient;

    @Autowired
    public PermissionFilter(PermissionClient permissionClient) {
        this.permissionClient = permissionClient;
    }

    @Override
    protected Boolean interceptCheck(HttpServletRequest request,
                                     HttpServletResponse response)
            throws Exception {

        // Performs the permission checking logic
        // Get the userId from the Header.Long userId = Long.valueOf(request.getHeader("userId"));
        String uri = request.getRequestURI().substring("/example".length());
        String httpMethod = request.getMethod();

        return permissionClient.checkPermission(
                new CheckPermissionRequest(userId, uri, httpMethod)
        );
    }

    @Override
    protected int getHttpStatus() {
        return HttpStatus.OK.value();
    }

    @Override
    protected String getErrorMsg() {

        CommonResponse<Object> response = new CommonResponse<>();
        response.setCode(-2);
        response.setMessage("You do not have permission to operate");

        return JSON.toJSONString(response);
    }
}
