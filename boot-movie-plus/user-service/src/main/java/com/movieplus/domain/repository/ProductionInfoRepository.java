package com.movieplus.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movieplus.domain.entity.ProductionInfo;

@Repository
public interface ProductionInfoRepository extends JpaRepository<ProductionInfo, String>{

}
