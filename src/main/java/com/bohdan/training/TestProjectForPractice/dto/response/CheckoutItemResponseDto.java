package com.bohdan.training.TestProjectForPractice.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CheckoutItemResponseDto {

    private Long productId;

    private String productName;

    private Integer qty;

    private BigDecimal pricePerUnit;

    private BigDecimal totalPrice;
}