package com.bohdan.training.TestProjectForPractice.dto.response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusDto extends IdDto {
    @NotBlank
    private String name;
}
