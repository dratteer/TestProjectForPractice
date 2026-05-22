package com.bohdan.training.TestProjectForPractice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "id", "lastName", "firstName", "fatherName"})
@Getter
@Setter
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
}
