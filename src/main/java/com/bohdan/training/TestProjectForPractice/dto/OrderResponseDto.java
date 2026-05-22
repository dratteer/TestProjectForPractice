package com.bohdan.training.TestProjectForPractice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonPropertyOrder({ "id", "client", "sum", "date"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private LocalDateTime date;
    private ClientDto client;
    private BigDecimal sum;
}
