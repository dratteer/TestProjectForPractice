package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.*;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.request.UpdateOrderDetailQtyDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.entity.Product;

import java.util.List;

public interface OrderDetailService{
    List<OrderDetailDto> getAll();

    OrderDetailDto getById(Long id);

    IdDto create(OrderDetailUpsertDto dto);

    void patch(Long id, Integer qty);

    void delete(Long id);

    void updateQty(Long orderDetailId, UpdateOrderDetailQtyDto dto);
}
