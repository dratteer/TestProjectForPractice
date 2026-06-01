package com.bohdan.training.TestProjectForPractice.dto.response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@JsonPropertyOrder({ "id", "name", "brand", "price", "description", "cost" })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends IdDto {
    @NotBlank
    private String name;

    private BrandDto brand;

    @Positive(message = "Cost должна быть положительной")
    private BigDecimal cost;

    @Positive(message = "Price должна быть положительной")
    private BigDecimal price;

    private String description;

    @Positive
    private Integer stockQty;

    private StatusDto status;
}
