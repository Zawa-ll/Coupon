package com.example.coupon.dao;

import com.example.coupon.constant.CouponStatus;
import com.example.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Coupon Dao 
 */
public interface CouponDao extends JpaRepository<Coupon, Integer> {

    /**
     * Find coupon records based on userId + status
     * where userId = ... and status = ...
     * */
    List<Coupon> findAllByUserIdAndStatus(Long userId, CouponStatus status);

    /**
     * Finding coupon records based on userId
     * */
    List<Coupon> findAllByUserId(Long userId);
}
