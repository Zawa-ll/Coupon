package com.example.coupon.vo;

import com.example.coupon.constant.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRule {

    /** Coupon expiration rules */
    private Expiration expiration;

    private Discount discount;

    /** Maximum number of tickets per person */
    private Integer limitation;

    /** Scope of use: geography + commodity type */
    private Usage usage;

    /** Weights (which coupons can be stacked, coupons of the same type must not be stacked): list[], unique code of the coupon */
    private String weight;

    public boolean validate() {

        return expiration.validate() && discount.validate()
                && limitation > 0 && usage.validate()
                && StringUtils.isNotEmpty(weight);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Expiration {

        /** Expiration date rule, corresponds to code field of PeriodType */
        private Integer period;

        /** Validity interval: valid only for variable validity periods */
        private Integer gap;

        /** Expiration date of coupon template, both types of rules are valid */
        private Long deadline;

        boolean validate() {
            // Minimized checksum
            return null != PeriodType.of(period) && gap > 0 && deadline > 0;
        }
    }

    /**
     * Discounts, to be determined in conjunction with type
     * */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Discount {

        /** Amount: Minimum(20), Discount(85), Minimum(10) */
        private Integer quota;

        /** Benchmarks, how many are required to be available */
        private Integer base;

        boolean validate() {

            return quota > 0 && base > 0;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Usage {

        private String province;
        private String city;
        private String goodsType;

        boolean validate() {

            return StringUtils.isNotEmpty(province)
                    && StringUtils.isNotEmpty(city)
                    && StringUtils.isNotEmpty(goodsType);
        }
    }
}
