package com.bohdan.training.TestProjectForPractice.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class SupplierApiDto {
    private Long id;
    private String name;
    private Integer qty;
    private BigDecimal cost;
}