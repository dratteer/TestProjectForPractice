package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.Request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.Response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import com.bohdan.training.TestProjectForPractice.mapper.OrderDetailMapper;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    @Override
    public List<OrderDetailDto> getAll() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();

        return orderDetailMapper.toDtoList(orderDetails);
    }

    @Override
    public OrderDetailDto getById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("order not found"));

        return orderDetailMapper.toDto(orderDetail);
    }

    @Override
    public IdDto create(OrderDetailUpsertDto dto) {
        OrderDetail entity = orderDetailMapper.toEntity(dto);
        var product = productRepository.findById(dto.getProductId())
            .orElseThrow(() -> new RuntimeException("Product not found"));
        entity.setPrice(product.getPrice());
        OrderDetail saved = orderDetailRepository.save(entity);

        orderService.calculateTotal(dto.getOrderId());

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, OrderDetailUpsertDto updatedDto) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        orderDetailMapper.updateOrderDetailFromDto(updatedDto, existing);
        orderDetailRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }
}
