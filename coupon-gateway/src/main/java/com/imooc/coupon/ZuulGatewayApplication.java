package com.example.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Gateway application launch portal
 * @EnableZuulProxy identifies the current application as Zuul Server
 * @SpringCloudApplication combines SpringBoot application + service discovery + fusion.*/
@EnableZuulProxy
@EnableFeignClients     // The Feign interface is written in permission-sdk.
@SpringCloudApplication
public class ZuulGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
