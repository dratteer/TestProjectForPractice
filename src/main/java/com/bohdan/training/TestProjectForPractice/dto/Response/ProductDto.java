package com.bohdan.training.TestProjectForPractice.dto.Response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    private String name;
    private BrandDto brand;
    private BigDecimal cost;
    private BigDecimal price;
    private String description;
}
