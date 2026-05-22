package com.bohdan.training.TestProjectForPractice.dto;

import com.bohdan.training.TestProjectForPractice.entity.Order;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.math.BigDecimal;

@JsonPropertyOrder({ "id", "order", "product", "qty", "price"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponseDto {
    private Long id;
    private OrderResponseDto order;
    private ProductResponseDto product;
    private Integer qty;
    private BigDecimal price;
}
