package com..coupon.converter;

import com..coupon.constant.CouponCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
* Coupon Category Enumeration Attribute Converter
 * AttributeConverter<X, Y>
 * X: is the type of the entity attribute
 * Y: is the type of the database field
 */
@Converter
public class CouponCategoryConverter
        implements AttributeConverter<CouponCategory, String> {

    /**
     * Converts entity attribute X to Y and stores it in the database, with actions performed on inserts and updates.
     * */
    @Override
    public String convertToDatabaseColumn(CouponCategory couponCategory) {
        return couponCategory.getCode();
    }

    /**
     * Converts a field Y in the database to an entity attribute X. Actions performed during query operations
     * */
    @Override
    public CouponCategory convertToEntityAttribute(String code) {
        return CouponCategory.of(code);
    }
}
