package com.example.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * fake commodity information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfo {

    private Integer type;

    private Double price;

    private Integer count;

}
