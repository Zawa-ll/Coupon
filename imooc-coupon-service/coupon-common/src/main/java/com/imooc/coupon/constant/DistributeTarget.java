package com.example.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Distribution of targets
 */
@Getter
@AllArgsConstructor
public enum DistributeTarget {

    SINGLE("single-user", 1),
    MULTI("Multi-user", 2).

    /** Distribution of description of objectives */
    private String description;

    /** Distribution target code */
    private Integer code;

    public static DistributeTarget of(Integer code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
