package com.bohdan.training.TestProjectForPractice.repository;

import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
