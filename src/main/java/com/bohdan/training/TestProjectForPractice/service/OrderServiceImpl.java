package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderResponseDto;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import com.bohdan.training.TestProjectForPractice.mapper.OrderMapper;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import com.bohdan.training.TestProjectForPractice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponseDto> getAll() {
        List<Order> orders = orderRepository.findAll();

        return orderMapper.toDtoList(orders);
    }

    @Override
    public OrderResponseDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order not found"));

        return orderMapper.toDto(order);
    }

    @Override
    public IdDto create(OrderCreateUpdateDto dto) {
        Order entity = orderMapper.toEntity(dto);
        Order saved = orderRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, OrderCreateUpdateDto updatedDto) {
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
        var total = orderDetailRepository.calculateTotalByOrderId(id);

        var order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setSum(total);
    }
}
