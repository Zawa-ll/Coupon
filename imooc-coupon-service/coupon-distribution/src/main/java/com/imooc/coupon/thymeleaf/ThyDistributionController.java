package com.example.coupon.thymeleaf;

import com.alibaba.fastjson.JSON;
import com.example.coupon.dao.CouponDao;
import com.example.coupon.entity.Coupon;
import com.example.coupon.exception.CouponException;
import com.example.coupon.feign.TemplateClient;
import com.example.coupon.service.IUserService;
import com.example.coupon.vo.AcquireTemplateRequest;
import com.example.coupon.vo.CouponTemplateSDK;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 优惠券分发 Controller
  
 */
@Slf4j
@Controller
@RequestMapping("/distribution/thy")
public class ThyDistributionController {

    private final CouponDao couponDao;

    private final IUserService userService;

    private final TemplateClient templateClient;

    @Autowired
    public ThyDistributionController(CouponDao couponDao, IUserService userService,
                                     TemplateClient templateClient) {
        this.couponDao = couponDao;
        this.userService = userService;
        this.templateClient = templateClient;
    }

    /**
     * 127.0.0.1/coupon-distribution/distribution/thy/users
     * */
    @GetMapping("/users")
    public String users(ModelMap map) {

        log.info("view all user coupons.");

        List<Coupon> coupons = couponDao.findAll();
        List<ThyCouponInfo> infos = coupons.stream()
                .map(ThyCouponInfo::to).collect(Collectors.toList());
        map.addAttribute("coupons", infos);

        return "users_coupon_list";
    }

    @GetMapping("/user/{userId}")
    public String user(@PathVariable Long userId, ModelMap map) {

        log.info("view user: {} coupons.", userId);

        List<Coupon> coupons = couponDao.findAllByUserId(userId);
        List<ThyCouponInfo> infos = coupons.stream()
                .map(ThyCouponInfo::to).collect(Collectors.toList());
        map.addAttribute("coupons", infos);
        map.addAttribute("uid", userId);

        return "user_coupon_list";
    }

    @GetMapping("/template/{userId}")
    public String template(@PathVariable Long userId, ModelMap map) throws CouponException {

        log.info("view user: {} can acquire template.", userId);

        List<CouponTemplateSDK> templateSDKS = userService.findAvailableTemplate(userId);
        List<ThyTemplateInfo> infos = templateSDKS.stream()
                .map(ThyTemplateInfo::to).collect(Collectors.toList());
        infos.forEach(i -> i.setUserId(userId));

        map.addAttribute("templates", infos);

        return "template_list";
    }

    @GetMapping("/template/info")
    public String templateInfo(@RequestParam Long uid, @RequestParam Integer id, ModelMap map) {

        log.info("user view template info: {} -> {}", uid, id);

        Map<Integer, CouponTemplateSDK> id2Template = templateClient.findIds2TemplateSDK(
                Collections.singletonList(id)
        ).getData();

        if (MapUtils.isNotEmpty(id2Template)) {
            ThyTemplateInfo info = ThyTemplateInfo.to(id2Template.get(id));
            info.setUserId(uid);
            map.addAttribute("template", info);
        }

        return "template_detail";
    }

    @GetMapping("/acquire")
    public String acquire(@RequestParam Long uid, @RequestParam Integer tid) throws CouponException {

        log.info("user {} acquire template {}.", uid, tid);

        Map<Integer, CouponTemplateSDK> id2Template = templateClient.findIds2TemplateSDK(
                Collections.singletonList(tid)
        ).getData();
        if (MapUtils.isNotEmpty(id2Template)) {
            log.info("user acquire coupon: {}", JSON.toJSONString(userService.acquireTemplate(
                    new AcquireTemplateRequest(uid, id2Template.get(tid))
            )));
        }

        return "redirect:/distribution/thy/user/" + uid;
    }
}
