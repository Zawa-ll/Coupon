package com.example.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Type of validity period
 */
@Getter
@AllArgsConstructor
public enum PeriodType {

    REGULAR("Fixed (fixed date)", 1).
    SHIFT("Variable (from date of receipt)", 2).

    /** Description of the period of validity */
    private String description;

    /** Validity code */
    private Integer code;

    public static PeriodType of(Integer code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }
}
