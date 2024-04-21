package com.example.coupon.vo;

import lombok.Data;

/**
 * Interface authority information assembly class definition  
 */
@Data
public class PermissionInfo {
    
    private String url;

    private String method;

    private Boolean isRead;

    private String description;

    private String extra;

    @Override
    public String toString() {

        return "url = " + url
                + ", method = " + method
                + ", isRead = " + isRead
                + ", description = " + description;
    }
}
