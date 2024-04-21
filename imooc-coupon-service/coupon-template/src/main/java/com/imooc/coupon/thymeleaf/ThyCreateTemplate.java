package com.example.coupon.thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ThyCreateTemplate {

    private String name;

    private String logo;

    private String desc;

    private String category;

    private Integer productLine;

    private Integer count;

    private Long userId;

    private Integer target;

    private Integer period;

    private Integer gap;

    private String deadline = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    private Integer quota;

    private Integer base;

    private Integer limitation;

    private String province;

    private String city;

    private List<Integer> goodsType = new ArrayList<>();

    private String weight;
}
