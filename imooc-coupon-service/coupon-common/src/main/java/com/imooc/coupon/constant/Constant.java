package com.example.coupon.constant;

public class Constant {

    /** Topic for Kafka messages */
    public static final String TOPIC = "user_coupon_op";

     /**
     * Redis Key Prefix Definitions</h2
     * */
     public static class RedisPrefix {

        /** Coupon code key prefix */
        public static final String COUPON_TEMPLATE =
                "coupon_template_code_";

        /** All currently available coupon key prefixes for the user */
        public static final String USER_COUPON_USABLE =
                "user_coupon_usable_";

        /** All of the user's currently used coupons key prefix */
        public static final String USER_COUPON_USED =
                "user_coupon_used_";

        /** All of the user's current expired coupon key prefixes */
        public static final String USER_COUPON_EXPIRED =
                "user_coupon_expired_";
    }
}
