package com.bohdan.training.TestProjectForPractice.repository;

import com.bohdan.training.TestProjectForPractice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
