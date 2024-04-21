package com.example.coupon.service;

import com.example.coupon.entity.Coupon;
import com.example.coupon.exception.CouponException;
import com.example.coupon.vo.AcquireTemplateRequest;
import com.example.coupon.vo.CouponTemplateSDK;
import com.example.coupon.vo.SettlementInfo;

import java.util.List;

public interface IUserService {

    List<Coupon> findCouponsByStatus(Long userId, Integer status)
            throws CouponException;

    List<CouponTemplateSDK> findAvailableTemplate(Long userId)
        throws CouponException;

    Coupon acquireTemplate(AcquireTemplateRequest request)
            throws CouponException;

    SettlementInfo settlement(SettlementInfo info) throws CouponException;
}
