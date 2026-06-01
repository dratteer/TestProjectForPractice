package com.bohdan.training.TestProjectForPractice.dto.response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "id", "lastName", "firstName", "fatherName"})
@Getter
@Setter
public class ClientDto extends IdDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String fatherName;
}
