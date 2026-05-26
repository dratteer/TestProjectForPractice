package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.*;
import com.bohdan.training.TestProjectForPractice.dto.Request.OrderUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.Response.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(Long id);

    IdDto create(OrderUpsertDto dto);

    void update(Long id, OrderUpsertDto updatedDto);

    void delete(Long id);

    void calculateTotal(Long id);
}
