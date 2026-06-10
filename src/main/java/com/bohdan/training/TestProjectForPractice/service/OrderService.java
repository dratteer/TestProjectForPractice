package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.*;
import com.bohdan.training.TestProjectForPractice.dto.request.CheckoutDto;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.request.UpdateOrderDetailQtyDto;
import com.bohdan.training.TestProjectForPractice.dto.response.CheckoutResponseDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(Long id);

    IdDto create(OrderUpsertDto dto);

    void update(Long id, OrderUpsertDto updatedDto);

    void delete(Long id);

    void calculateTotal(Long id);

    CheckoutResponseDto checkout(CheckoutDto dto);

    void cancelOrder(Long id);
}
