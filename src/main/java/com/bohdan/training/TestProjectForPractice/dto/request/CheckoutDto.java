package com.bohdan.training.TestProjectForPractice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutDto {

    @NotNull
    private Long clientId;

    @NotEmpty
    private List<CheckoutItemDto> items;
}