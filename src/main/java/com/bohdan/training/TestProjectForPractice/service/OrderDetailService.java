package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.*;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDetailDto;

import java.util.List;

public interface OrderDetailService{
    List<OrderDetailDto> getAll();

    OrderDetailDto getById(Long id);

    IdDto create(OrderDetailUpsertDto dto);

    void update(Long id, OrderDetailUpsertDto updatedDto);

    void delete(Long id);
}
