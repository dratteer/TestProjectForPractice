package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.constance.OrderStatusConstance;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.CheckoutDto;
import com.bohdan.training.TestProjectForPractice.dto.request.CheckoutItemDto;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.CheckoutItemResponseDto;
import com.bohdan.training.TestProjectForPractice.dto.response.CheckoutResponseDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDto;
import com.bohdan.training.TestProjectForPractice.entity.*;
import com.bohdan.training.TestProjectForPractice.exception.EntityNotFoundException;
import com.bohdan.training.TestProjectForPractice.exception.InvalidStatusException;
import com.bohdan.training.TestProjectForPractice.mapper.OrderMapper;
import com.bohdan.training.TestProjectForPractice.repository.ClientRepository;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import com.bohdan.training.TestProjectForPractice.repository.OrderRepository;
import com.bohdan.training.TestProjectForPractice.repository.OrderStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ClientRepository clientRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderMapper orderMapper;
    private final CalculationStockService calculationStockService;

    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepository.findAll();

        return orderMapper.toDtoList(orders);
    }

    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));

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
                .orElseThrow(() -> new EntityNotFoundException("Order", id));

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
                .orElseThrow(() -> new EntityNotFoundException("Order", id));

        var total = orderDetailRepository.getTotalByOrderId(id);
        order.setSum(total);
    }

    @Transactional
    @Override
    public CheckoutResponseDto checkout(CheckoutDto dto) {

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client", dto.getClientId()));
        OrderStatus orderStatus = orderStatusRepository.findById(
                        (long) OrderStatusConstance.created)
                .orElseThrow(() -> new EntityNotFoundException("OrderStatus", (long) OrderStatusConstance.created));

        Order order = new Order();
        order.setClient(client);
        order.setDate(Instant.now());
        order.setSum(BigDecimal.ZERO);
        order.setOrderStatus(orderStatus);

        Order savedOrder = orderRepository.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<CheckoutItemResponseDto> responseItems = new ArrayList<>();

        for (CheckoutItemDto item : dto.getItems()) {

            Product product = calculationStockService.calcProductStockQty(
                    item.getProductId(),
                    item.getQty()
            );

            BigDecimal pricePerUnit = product.getPrice();

            BigDecimal itemTotal = pricePerUnit.multiply(BigDecimal.valueOf(item.getQty())
            );

            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProduct(product);
            detail.setQty(item.getQty());
            detail.setPrice(product.getPrice());

            orderDetailRepository.save(detail);

            totalAmount = totalAmount.add(itemTotal);

            CheckoutItemResponseDto responseItem = new CheckoutItemResponseDto();
            responseItem.setProductId(product.getId());
            responseItem.setProductName(product.getName());
            responseItem.setQty(item.getQty());
            responseItem.setPricePerUnit(pricePerUnit);
            responseItem.setTotalPrice(itemTotal);

            responseItems.add(responseItem);
        }

        savedOrder.setSum(totalAmount);

        CheckoutResponseDto response = new CheckoutResponseDto();
        response.setOrderId(savedOrder.getId());
        response.setClientId(client.getId());
        response.setTotalAmount(totalAmount);
        response.setOrderDate(savedOrder.getDate());
        response.setItems(responseItems);

        return response;
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", id));

        int orderStatusId = order.getOrderStatus().getId().intValue();

        if (orderStatusId == OrderStatusConstance.shipped) {
            throw new InvalidStatusException("Order", order.getId(), order.getOrderStatus().getName(), "cancel");
        }
        else if (orderStatusId == OrderStatusConstance.completed) {
            throw new InvalidStatusException("Order", order.getId(), order.getOrderStatus().getName(), "cancel");
        }
        else if (orderStatusId == OrderStatusConstance.canceled) {
            throw new InvalidStatusException("Order", order.getId(), order.getOrderStatus().getName(), "cancel");
        }

        List<OrderDetail> details = orderDetailRepository.findByOrderId(id);

        for (OrderDetail detail : details) {
            calculationStockService.returnProductStock(
                    detail.getProduct().getId(),
                    detail.getQty()
            );
        }

        OrderStatus cancelledStatus = orderStatusRepository.findById(
                        (long) OrderStatusConstance.canceled)
                .orElseThrow(() -> new EntityNotFoundException("OrderStatus", (long) OrderStatusConstance.canceled));

        order.setOrderStatus(cancelledStatus);
    }
}
