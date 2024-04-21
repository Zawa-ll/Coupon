package com.example.coupon.service;

import com.example.coupon.entity.CouponTemplate;
import com.example.coupon.exception.CouponException;
import com.example.coupon.vo.TemplateRequest;

/**
 * Build coupon template interface definition
 */
public interface IBuildTemplateService {

    CouponTemplate buildTemplate(TemplateRequest request)
            throws CouponException;
}
