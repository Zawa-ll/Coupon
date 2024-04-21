package com.example.coupon.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.coupon.constant.CouponStatus;
import com.example.coupon.converter.CouponStatusConverter;
import com.example.coupon.serialization.CouponSerialize;
import com.example.coupon.vo.CouponTemplateSDK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon")
@JsonSerialize(using = CouponSerialize.class)
public class Coupon {

    /** Self-augmenting primary key */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    /** Primary key (logical foreign key) of the associated coupon template */
    @Column(name = "template_id", nullable = false)
    private Integer templateId;

    /** Receiving users */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** Coupon code */
    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    /** Pick-up time */
    @CreatedDate
    @Column(name = "assign_time", nullable = false)
    private Date assignTime;

    @Column(name = "status", nullable = false)
    @Convert(converter = CouponStatusConverter.class)
    private CouponStatus status;

    /** Template information corresponding to user coupons */
    @Transient
    private CouponTemplateSDK templateSDK;

    /**
     * Returns an invalid Coupon object
     * */
    public static Coupon invalidCoupon() {

        Coupon coupon = new Coupon();
        coupon.setId(-1);
        return coupon;
    }
    
    public Coupon(Integer templateId, Long userId, String couponCode,
                  CouponStatus status) {

        this.templateId = templateId;
        this.userId = userId;
        this.couponCode = couponCode;
        this.status = status;
    }
}
