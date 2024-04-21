package com.example.coupon.feign.hystrix;

import com.example.coupon.exception.CouponException;
import com.example.coupon.feign.SettlementClient;
import com.example.coupon.vo.CommonResponse;
import com.example.coupon.vo.SettlementInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Settlement microservices fusion strategy implementation
 */
@Slf4j
@Component
public class SettlementClientHystrix implements SettlementClient {

    /**
     * :: Calculation of coupon rules
     * @param settlement {@link SettlementInfo}
     */
    @Override
    public CommonResponse<SettlementInfo> computeRule(SettlementInfo settlement)
            throws CouponException {

        log.error("[eureka-client-coupon-settlement] computeRule" +
                "request error");

        settlement.setEmploy(false);
        settlement.setCost(-1.0);

        return new CommonResponse<>(
                -1,
                "[eureka-client-coupon-settlement] request error",
                settlement
        );
    }
}
