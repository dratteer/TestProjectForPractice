package com.bohdan.training.TestProjectForPractice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateUpdateDto {
    private String name;
    private Long brandId;
    private BigDecimal price;
    private BigDecimal cost;
    private String description;
}