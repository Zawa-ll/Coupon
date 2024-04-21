package com.example.coupon.dao;

import com.example.coupon.entity.RolePathMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RolePathMapping Dao
 */
public interface RolePathMappingRepository
        extends JpaRepository<RolePathMapping, Integer> {

    /**
     * Finding Data Records by Role id + Path id
     * */
    RolePathMapping findByRoleIdAndPathId(Integer roleId, Integer pathId);
}
