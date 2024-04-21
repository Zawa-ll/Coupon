package com.example.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementInfo {
    private Long userId;
    private List<GoodsInfo> goodsInfos;
    private List<CouponAndTemplateInfo> couponAndTemplateInfos;

    /** Whether to effect settlement, i.e., write-off */
    private Boolean employ;

    private Double cost;

    /**
     * Coupon and template information
     * */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponAndTemplateInfo {

        private Integer id;

        /** Template object corresponding to the coupon */
        private CouponTemplateSDK template;
    }
}
