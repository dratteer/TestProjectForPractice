package com.bohdan.training.TestProjectForPractice.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateOrderDetailQtyDto {

    @Min(1)
    private Integer qty;
}