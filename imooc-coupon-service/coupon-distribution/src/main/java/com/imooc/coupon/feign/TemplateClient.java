package com.example.coupon.feign;

import com.example.coupon.feign.hystrix.TemplateClientHystrix;
import com.example.coupon.vo.CommonResponse;
import com.example.coupon.vo.CouponTemplateSDK;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Coupon template microservice Feign interface definition
 */
@FeignClient(value = "eureka-client-coupon-template",
        fallback = TemplateClientHystrix.class)
public interface TemplateClient {

        /**
        * Find all available coupon templates
        * */
        @RequestMapping(value = "/coupon-template/template/sdk/all",
            method = RequestMethod.GET)
        CommonResponse<List<CouponTemplateSDK>> findAllUsableTemplate();

        /**
        * Get the mapping of template ids to the CouponTemplateSDK
        * */
        @RequestMapping(value = "/coupon-template/template/sdk/infos",
                method = RequestMethod.GET)
        CommonResponse<Map<Integer, CouponTemplateSDK>> findIds2TemplateSDK(
                @RequestParam("ids") Collection<Integer> ids
        );
}
