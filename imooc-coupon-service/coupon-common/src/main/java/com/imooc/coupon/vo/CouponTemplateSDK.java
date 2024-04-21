package com.example.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Definition of coupon template information for use between microservices
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CouponTemplateSDK {

    private Integer id;
    private String name;
    private String logo;
    
    /** Coupon Description */
    private String desc;
    
    private String category;
    private Integer productLine;
    
    /** Coding of coupon templates */
    private String key;

    /** Target users */
    private Integer target;

    /** Coupon rules */
    private TemplateRule rule;
}
