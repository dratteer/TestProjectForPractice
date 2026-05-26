package com.bohdan.training.TestProjectForPractice.dto.Response;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "id", "lastName", "firstName", "fatherName"})
@Getter
@Setter
public class ClientDto extends IdDto {
    private String firstName;
    private String lastName;
    private String fatherName;
}
