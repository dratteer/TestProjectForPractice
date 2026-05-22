package com.bohdan.training.TestProjectForPractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailCreateUpdateDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer qty;
    private BigDecimal price;
}
