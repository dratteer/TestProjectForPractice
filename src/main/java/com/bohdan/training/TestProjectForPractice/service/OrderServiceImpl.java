package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDto;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import com.bohdan.training.TestProjectForPractice.mapper.OrderMapper;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import com.bohdan.training.TestProjectForPractice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();

        return orderMapper.toDtoList(orders);
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order not found"));

        return orderMapper.toDto(order);
    }

    @Override
    public IdDto create(OrderUpsertDto dto) {
        Order entity = orderMapper.toEntity(dto);
        entity.setDate(Instant.now());
        Order saved = orderRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, OrderUpsertDto updatedDto) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        orderMapper.updateOrderFromDto(updatedDto, existing);
        orderRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void calculateTotal(Long id){
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        var total = orderDetailRepository.getTotalByOrderId(id);
        order.setSum(total);
    }
}
