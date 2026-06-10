package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.constance.OrderStatusConstance;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.request.UpdateOrderDetailQtyDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.exception.EntityNotFoundException;
import com.bohdan.training.TestProjectForPractice.mapper.OrderDetailMapper;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
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
    private final CalculationStockService calculationStockService;

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

        calculationStockService.calcProductStockQty(dto.getProductId(), dto.getQty());

        OrderDetail entity = orderDetailMapper.toEntity(dto);
        entity.setPrice(
                product.getPrice()
        );
        OrderDetail saved = orderDetailRepository.save(entity);
        orderService.calculateTotal(dto.getOrderId());

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());

        return idDto;
    }

    @Override
    public void patch(Long id, Integer qty) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        int newQty = qty - existing.getQty();

        calculationStockService.calcProductStockQty(existing.getProduct().getId(), newQty);

        existing.setQty(qty);
        existing.setPrice(existing.getProduct().getPrice());

        orderDetailRepository.save(existing);

        orderService.calculateTotal(existing.getOrder().getId());
    }

    @Override
    public void delete(Long id) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        calculationStockService.calcProductStockQty(existing.getProduct().getId(), - existing.getQty());

        orderDetailRepository.deleteById(id);

        orderService.calculateTotal(existing.getOrder().getId());
    }

    @Override
    @Transactional
    public void updateQty(Long orderDetailId, UpdateOrderDetailQtyDto dto) {

        OrderDetail detail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        Order order = detail.getOrder();

        int orderStatusId = order.getOrderStatus().getId().intValue();

        if (orderStatusId == OrderStatusConstance.shipped
                || orderStatusId == OrderStatusConstance.completed
                || orderStatusId == OrderStatusConstance.canceled) {

            throw new RuntimeException("Order cannot be modified");
        }

        Product product = detail.getProduct();

        int oldQty = detail.getQty();
        int newQty = dto.getQty();

        int delta = newQty - oldQty;

        if (delta > 0) {

            calculationStockService.calcProductStockQty(product.getId(), delta);

        } else if (delta < 0) {

            calculationStockService.returnProductStock(product.getId(), Math.abs(delta));
        }

        detail.setQty(newQty);

        BigDecimal newPrice = product.getPrice().multiply(BigDecimal.valueOf(newQty));

        detail.setPrice(newPrice);

        orderService.calculateTotal(order.getId()
        );
    }
}
