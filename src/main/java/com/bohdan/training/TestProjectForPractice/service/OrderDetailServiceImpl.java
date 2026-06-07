package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.constance.ProductStatusConstance;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.entity.ProductStatus;
import com.bohdan.training.TestProjectForPractice.exception.EntityNotFoundException;
import com.bohdan.training.TestProjectForPractice.mapper.OrderDetailMapper;
import com.bohdan.training.TestProjectForPractice.repository.OrderDetailRepository;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
import com.bohdan.training.TestProjectForPractice.repository.ProductStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService{
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final ProductStatusRepository productStatusRepository;

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

        calcProductStockQty(dto.getProductId(), dto.getQty());

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

        calcProductStockQty(existing.getProduct().getId(), newQty);

        existing.setQty(qty);
        existing.setPrice(existing.getProduct().getPrice());

        orderDetailRepository.save(existing);

        orderService.calculateTotal(existing.getOrder().getId());
    }

    @Override
    public void delete(Long id) {
        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderDetail not found"));

        calcProductStockQty(existing.getProduct().getId(), - existing.getQty());

        orderDetailRepository.deleteById(id);

        orderService.calculateTotal(existing.getOrder().getId());
    }

    public void calcProductStockQty(Long productId, Integer qty) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int statusId = product.getProductStatus().getId().intValue();

        if (statusId == ProductStatusConstance.discontinued) {
            throw new RuntimeException("Product is discontinued");
        }
        else if (statusId == ProductStatusConstance.comingSoon) {
            throw new RuntimeException("Product is coming soon");
        }

        int newQty = product.getStockQty() - qty;
        if (newQty < 0) {
           throw new RuntimeException("Product is out of stock");
        }

        product.setStockQty(newQty);

        if (newQty == 0) {
            ProductStatus outOfStock = productStatusRepository.findById((long) ProductStatusConstance.outOfStock)
                    .orElseThrow(() -> new RuntimeException("ProductStatus not found"));

            product.setProductStatus(outOfStock);
        }
        else if (product.getProductStatus().getId().intValue() != ProductStatusConstance.available) {
            ProductStatus available = productStatusRepository.findById((long) ProductStatusConstance.available)
                    .orElseThrow(() -> new RuntimeException("ProductStatus not found"));

            product.setProductStatus(available);
        }
    }
}
