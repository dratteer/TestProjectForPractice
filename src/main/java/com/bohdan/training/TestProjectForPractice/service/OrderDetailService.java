package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.*;

import java.util.List;

public interface OrderDetailService{
    List<OrderDetailResponseDto> getAll();

    OrderDetailResponseDto getById(Long id);

    IdDto create(OrderDetailCreateUpdateDto dto);

    void update(Long id, OrderDetailCreateUpdateDto updatedDto);

    void delete(Long id);
}
