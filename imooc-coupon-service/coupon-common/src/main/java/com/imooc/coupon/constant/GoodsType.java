package com.example.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Enumeration of commodity types
 */
@Getter
@AllArgsConstructor
public enum GoodsType {

    ENTAIN("Cultural and Entertainment", 1),
    HOUSEHOLD("Household", 3),
    OTHERS("Fresh", 2)
    ALL("All Categories", 5).

    private String description;

    /** Commodity type code */
    private Integer code;

    public static GoodsType of(Integer code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(code + " not exists!")
                );
    }
}
