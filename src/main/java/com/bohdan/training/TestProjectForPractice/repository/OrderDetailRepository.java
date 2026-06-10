package com.bohdan.training.TestProjectForPractice.repository;

import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("select coalesce(sum(od.qty * od.price), 0) from OrderDetail od where od.order.id = :orderId")
    BigDecimal getTotalByOrderId(@Param("orderId") Long orderId);

    List<OrderDetail> findByOrderId(Long orderId);
}
