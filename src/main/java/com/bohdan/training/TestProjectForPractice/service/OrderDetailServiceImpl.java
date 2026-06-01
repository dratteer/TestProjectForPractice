package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.constance.StatusConstance;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.entity.Status;
import com.bohdan.training.TestProjectForPractice.exception.EntityNotFoundException;
import com.bohdan.training.TestProjectForPractice.mapper.OrderDetailMapper;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
import com.bohdan.training.TestProjectForPractice.repository.StatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final StatusRepository statusRepository;

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
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        /*
        switch (product.getStatus().getId().intValue()) {
            case StatusConstance.available:
                break;
            case StatusConstance.outOfStock:
                throw new RuntimeException("Product is out of stock");
            case StatusConstance.discontinued:
                throw new RuntimeException("Product is discontinued");
            case StatusConstance.comingSoon:
                throw new RuntimeException("Product is not available yet");
            default:
                throw new RuntimeException("Unknown product status");
        }

        if (product.getStockQty() < dto.getQty()) {
            throw new RuntimeException("Not enough stock. Available: " + product.getStockQty());
        }

        product.setStockQty(product.getStockQty() - dto.getQty());

        if (product.getStockQty() == 0) {
            Status outOfStockStatus = statusRepository
                    .findById((long) StatusConstance.outOfStock)
                    .orElseThrow(() -> new RuntimeException("Status not found"));
            product.setStatus(outOfStockStatus);
        }

        productRepository.save(product);

         */

        calculation(dto.getProductId(), dto.getQty());

        OrderDetail entity = orderDetailMapper.toEntity(dto);
        entity.setPrice(
                product.getPrice().multiply(BigDecimal.valueOf(dto.getQty()))
        );
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

        int newQty = updatedDto.getQty() - existing.getQty();

        calculation(updatedDto.getProductId(), newQty);

        orderDetailMapper.updateOrderDetailFromDto(updatedDto, existing);
        orderDetailRepository.save(existing);

        orderService.calculateTotal(existing.getOrder().getId());
    }

    @Override
    public void delete(Long id) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        calculation(existing.getOrder().getId(), - existing.getQty());

        orderDetailRepository.deleteById(id);

        orderService.calculateTotal(existing.getOrder().getId());
    }

    public void calculation(Long productId, Integer qty){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        int newQty = product.getStockQty() - qty;
        if (newQty < 0) {
           throw new RuntimeException("Product is out of stock");
        }

        product.setStockQty(newQty);
    }
}
