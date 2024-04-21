package com.example.coupon.service;

import com.example.coupon.entity.CouponTemplate;

public interface IAsyncService {

    /**
     * Asynchronous creation of coupon codes based on templates
     * */
    void asyncConstructCouponByTemplate(CouponTemplate template);
}
