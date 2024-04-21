package com.example.coupon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    ADMIN("Administrator"),
    SUPER_ADMIN("Super Administrator"),
    CUSTOMER("Ordinary User").
    
    private String roleName;
}
