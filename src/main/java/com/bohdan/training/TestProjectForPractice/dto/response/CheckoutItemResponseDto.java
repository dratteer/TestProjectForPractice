package com.bohdan.training.TestProjectForPractice.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CheckoutItemResponseDto {

    private Long productId;

    private String productName;

    private Integer qty;

    private BigDecimal pricePerUnit;

    private BigDecimal totalPrice;
}