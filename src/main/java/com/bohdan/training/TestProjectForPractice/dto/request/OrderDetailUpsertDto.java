package com.bohdan.training.TestProjectForPractice.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailUpsertDto {
    private Long orderId;
    private Long productId;
    private Integer qty;
}
