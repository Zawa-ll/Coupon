package com.example.coupon.dao;

import com.example.coupon.entity.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRoleMapping Dao
  
 */
public interface UserRoleMappingRepository
        extends JpaRepository<UserRoleMapping, Long> {

    UserRoleMapping findByUserId(Long userId);
}
