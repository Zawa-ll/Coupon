package com.example.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Coupon Category
 */
@Getter
@AllArgsConstructor
public enum CouponCategory {

    COUPON1("Discount Coupon1", "001"),
    COUPON2("Discount Coupon2", "002"),
    Coupon3("Discount Coupon3", "003").

    /** Coupon description (categorized) */
    private String description;

    /** Coupon classification code */
    private String code;

    public static CouponCategory of(String code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
