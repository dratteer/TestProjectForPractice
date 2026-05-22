package com.bohdan.training.TestProjectForPractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateUpdateDto {
    private Long id;
    private LocalDateTime date;
    private Long clientId;
    private BigDecimal sum;
}
