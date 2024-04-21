package com.example.coupon.service;

import com.example.coupon.entity.CouponTemplate;
import com.example.coupon.exception.CouponException;
import com.example.coupon.vo.CouponTemplateSDK;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ITemplateBaseService {

    CouponTemplate buildTemplateInfo(Integer id) throws CouponException;

    List<CouponTemplateSDK> findAllUsableTemplate();

    /**
     * Get the mapping of template ids to the CouponTemplateSDK
     * */
    Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(Collection<Integer> ids);
}
