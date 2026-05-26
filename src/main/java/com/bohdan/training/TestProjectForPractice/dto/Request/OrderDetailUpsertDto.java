package com.bohdan.training.TestProjectForPractice.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailUpsertDto {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer qty;
}
