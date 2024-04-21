package com.example.coupon.dao;

import com.example.coupon.entity.Path;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Path Dao
  
 */
public interface PathRepository extends JpaRepository<Path, Integer> {

    /**
     * Finding path records by service name
     * */
    List<Path> findAllByServiceName(String serviceName);

    /**
     * Find Data Records by Path Pattern + Request Type
     * */
    Path findByPathPatternAndHttpMethod(String pathPattern, String httpMethod);
}
