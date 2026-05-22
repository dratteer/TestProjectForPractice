package com.bohdan.training.TestProjectForPractice.repository;

import com.bohdan.training.TestProjectForPractice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
