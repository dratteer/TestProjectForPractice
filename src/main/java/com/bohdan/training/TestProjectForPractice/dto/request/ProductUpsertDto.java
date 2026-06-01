package com.bohdan.training.TestProjectForPractice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpsertDto {
    @NotBlank
    private String name;

    @Positive
    @NotNull
    private Long brandId;

    @Positive
    private BigDecimal price;

    @Positive
    private BigDecimal cost;

    private String description;

    private Integer stockQty;
}