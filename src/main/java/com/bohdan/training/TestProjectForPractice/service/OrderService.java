package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.*;

import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAll();

    OrderResponseDto getById(Long id);

    IdDto create(OrderCreateUpdateDto dto);

    void update(Long id, OrderCreateUpdateDto updatedDto);

    void delete(Long id);
}
