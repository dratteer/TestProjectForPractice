package com.bohdan.training.TestProjectForPractice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpsertDto {
    private Long clientId;
    private Long orderStatusId;
}
