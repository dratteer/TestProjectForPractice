package com.bohdan.training.TestProjectForPractice.dto.response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@JsonPropertyOrder({ "id", "client", "sum", "date"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends IdDto {
    private Instant date;
    private ClientDto client;

    @Positive(message = "Sum должна быть положительной")
    private BigDecimal sum;
}
