package com.portfolio.repository;

import com.portfolio.model.AboutInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutInfoRepository extends JpaRepository<AboutInfo, Integer> {
}
