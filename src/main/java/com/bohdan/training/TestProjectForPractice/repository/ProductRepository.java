package com.bohdan.training.TestProjectForPractice.repository;

import com.bohdan.training.TestProjectForPractice.entity.OrderStatus;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySupplierProductId(Long supplierProductId);
}
