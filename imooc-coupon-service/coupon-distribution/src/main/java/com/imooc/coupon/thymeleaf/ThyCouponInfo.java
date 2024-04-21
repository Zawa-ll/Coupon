package com.example.coupon.thymeleaf;

import com.example.coupon.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;


@Data
@NoArgsConstructor
@AllArgsConstructor
class ThyCouponInfo {

    private Integer id;

    private Integer templateId;

    private Long userId;

    private String couponCode;

    private String assignTime;

    private String status;

    static ThyCouponInfo to(Coupon coupon) {

        ThyCouponInfo info = new ThyCouponInfo();
        info.setId(coupon.getId());
        info.setTemplateId(coupon.getTemplateId());
        info.setUserId(coupon.getUserId());
        info.setCouponCode(coupon.getCouponCode());
        info.setAssignTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(coupon.getAssignTime()));
        info.setStatus(coupon.getStatus().getDescription());

        return info;
    }
}
