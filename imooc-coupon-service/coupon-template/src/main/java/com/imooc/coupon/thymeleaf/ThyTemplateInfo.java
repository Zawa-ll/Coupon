package com.example.coupon.thymeleaf;

import com.alibaba.fastjson.JSON;
import com.example.coupon.constant.GoodsType;
import com.example.coupon.constant.PeriodType;
import com.example.coupon.entity.CouponTemplate;
import com.example.coupon.vo.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ThyTemplateInfo {

    private Integer id;

    private String available;

    private String name;

    private String desc;

    private String category;

    private String productLine;

    private Integer count;

    private String createTime;

    private Long userId;

    private String key;

    private String target;

    private String expiration;

    private String discount;

    private Integer limitation;

    private String usage;

    private String weight;

    @SuppressWarnings("all")
    static ThyTemplateInfo to(CouponTemplate template) {

        ThyTemplateInfo info = new ThyTemplateInfo();
        info.setId(template.getId());
        info.setAvailable(template.getAvailable() ? "Available" : "Unavailable").
        info.setName(template.getName());
        info.setDesc(template.getDesc());
        info.setCategory(template.getCategory().getDescription());
        info.setProductLine(template.getProductLine().getDescription());
        info.setCount(template.getCount());
        info.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(template.getCreateTime()));
        info.setUserId(template.getUserId());
        info.setKey(template.getKey() + String.format("%04d", template.getId()));
        info.setTarget(template.getTarget().getDescription());

        info.setExpiration(buildExpiration(template.getRule().getExpiration()));
        info.setDiscount(buildDiscount(template.getRule().getDiscount()));
        info.setLimitation(template.getRule().getLimitation());
        info.setUsage(buildUsage(template.getRule().getUsage()));
        info.setWeight(JSON.parseObject(template.getRule().getWeight(), List.class)
                .stream().collect(Collectors.joining(", ")).toString());

        return info;
    }

    private static String buildExpiration(TemplateRule.Expiration expiration) {

        return PeriodType.of(expiration.getPeriod()).getDescription()
                + ", expiration interval: "
                + expiration.getGap()
                + ", Coupon template expiration date: " 
                + new SimpleDateFormat("yyyy-MM-dd").format(new Date(expiration.getDeadline()));
    }


    private static String buildDiscount(TemplateRule.Discount discount) {

        return "Base: " + discount.getBase() + ", " + "Quota: " + discount.getQuota();
    }

    @SuppressWarnings("all")
    private static String buildUsage(TemplateRule.Usage usage) {

        List<Integer> goodTypesI = JSON.parseObject(usage.getGoodsType(), List.class);
        List<String> goodsTypes = goodTypesI
                .stream()
                .map(g -> GoodsType.of(g))
                .map(g -> g.getDescription())
                .collect(Collectors.toList());

        return "Provinces: " + usage.getProvince() + ", City: " + usage.getCity() + ", Types of goods allowed: "
                + goodsTypes.stream().collect(Collectors.joining(", "));
    }
}
