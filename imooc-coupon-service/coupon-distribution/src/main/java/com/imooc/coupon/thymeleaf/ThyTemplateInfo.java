package com.example.coupon.thymeleaf;

import com.alibaba.fastjson.JSON;
import com.example.coupon.constant.CouponCategory;
import com.example.coupon.constant.GoodsType;
import com.example.coupon.constant.PeriodType;
import com.example.coupon.constant.ProductLine;
import com.example.coupon.vo.CouponTemplateSDK;
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

    private Long userId;

    private Integer id;

    private String name;

    private String desc;

    private String category;

    private String productLine;

    private String expiration;

    private String discount;

    private String usage;

    static ThyTemplateInfo to(CouponTemplateSDK template) {

        ThyTemplateInfo info = new ThyTemplateInfo();
        info.setId(template.getId());
        info.setName(template.getName());
        info.setDesc(template.getDesc());
        info.setCategory(CouponCategory.of(template.getCategory()).getDescription());
        info.setProductLine(ProductLine.of(template.getProductLine()).getDescription());

        info.setExpiration(buildExpiration(template.getRule().getExpiration()));
        info.setDiscount(buildDiscount(template.getRule().getDiscount()));
        info.setUsage(buildUsage(template.getRule().getUsage()));

        return info;
    }

    private static String buildExpiration(TemplateRule.Expiration expiration) {

        return PeriodType.of(expiration.getPeriod()).getDescription()
                + ", expiration interval: "
                + expiration.getGap()
                + ", Coupon template expiration date: "
                + new SimpleDateFormat("yyyy-MM-dd").format(new Date(expiration.getDeadline()));
    }

    /**
     * Discount Rules Description
     * */
    private static String buildDiscount(TemplateRule.Discount discount) {

        return "Base: " + discount.getBase() + ", " + "Quota: " + discount.getQuota();
    }

    /**
     * Description of conditions of use
     * */
    @SuppressWarnings("all")
    private static String buildUsage(TemplateRule.Usage usage) {

        List<Integer> goodTypesI = JSON.parseObject(usage.getGoodsType(), List.class);
        List<String> goodsTypes = goodTypesI
                .stream()
                .map(g -> GoodsType.of(g))
                .map(g -> g.getDescription())
                .collect(Collectors.toList());

        return "Province: " + usage.getProvince() + ", City: " + usage.getCity() + ", Types of goods allowed: "
                + goodsTypes.stream().collect(Collectors.joining(", "));
    }
}
