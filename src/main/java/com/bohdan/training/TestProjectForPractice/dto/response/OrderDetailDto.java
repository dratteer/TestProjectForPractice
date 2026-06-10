package com.bohdan.training.TestProjectForPractice.dto.response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@JsonPropertyOrder({ "id", "order", "product", "qty", "price"})
@Getter
@Setter
@NoArgsConstructor
public class OrderDetailDto extends IdDto {
    private OrderDto order;
    private ProductDto product;

    @Min(1)
    @Max(100)
    private Integer qty;

    @Positive(message = "Price должна быть положительной")
    private BigDecimal price;
}
