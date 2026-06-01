package com.bohdan.training.TestProjectForPractice.dto.response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDto extends IdDto {
    @NotBlank
    private String name;
}
