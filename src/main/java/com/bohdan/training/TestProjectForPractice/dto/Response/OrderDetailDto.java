package com.bohdan.training.TestProjectForPractice.dto.Response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@JsonPropertyOrder({ "id", "order", "product", "qty", "price"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto extends IdDto {
    private OrderDto order;
    private ProductDto product;
    private Integer qty;
    private BigDecimal price;
}
