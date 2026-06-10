package com.bohdan.training.TestProjectForPractice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CheckoutItemDto {

    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private Integer qty;
}