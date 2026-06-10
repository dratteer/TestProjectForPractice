package com.bohdan.training.TestProjectForPractice.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
public class CheckoutResponseDto {

    private Long orderId;

    private Long clientId;

    private BigDecimal totalAmount;

    private Instant orderDate;

    private List<CheckoutItemResponseDto> items;
}