package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderDetailCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderDetailResponseDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import com.bohdan.training.TestProjectForPractice.mapper.OrderDetailMapper;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailResponseDto> getAll() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();

        return orderDetailMapper.toDtoList(orderDetails);
    }

    @Override
    public OrderDetailResponseDto getById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order not found"));

        return orderDetailMapper.toDto(orderDetail);
    }

    @Override
    public IdDto create(OrderDetailCreateUpdateDto dto) {
        OrderDetail entity = orderDetailMapper.toEntity(dto);
        OrderDetail saved = orderDetailRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, OrderDetailCreateUpdateDto updatedDto) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        orderDetailMapper.updateOrderDetailFromDto(updatedDto, existing);
        orderDetailRepository.save(existing);
    }

    @Override
    public  void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
