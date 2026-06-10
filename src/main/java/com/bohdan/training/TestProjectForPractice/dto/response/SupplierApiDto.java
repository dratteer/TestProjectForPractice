package com.bohdan.training.TestProjectForPractice.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplierApiDto {
    private Long id;
    private String name;
    private Integer qty;
    private BigDecimal cost;
}