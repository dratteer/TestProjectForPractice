package com.bohdan.training.TestProjectForPractice;

import com.bohdan.training.TestProjectForPractice.constance.ProductStatusConstance;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.entity.ProductStatus;
import com.bohdan.training.TestProjectForPractice.exception.ProductOutOfStockException;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
import com.bohdan.training.TestProjectForPractice.repository.ProductStatusRepository;
import com.bohdan.training.TestProjectForPractice.service.CalculationStockServiceImpl;
import com.bohdan.training.TestProjectForPractice.service.OrderDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CalculationStockServiceImpl calculationStockService;

    @Test
    void shouldDecreaseProductStockQty() {
        ProductStatus availableStatus = new ProductStatus();
        availableStatus.setId((long) ProductStatusConstance.available);

        Product product = new Product();
        product.setId(1L);
        product.setStockQty(10);
        product.setProductStatus(availableStatus);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        calculationStockService.calcProductStockQty(1L, 3);

        assertEquals(7, product.getStockQty());
    }

    @Test
    void shouldThrowExceptionWhenNotEnoughStock() {
        ProductStatus availableStatus = new ProductStatus();
        availableStatus.setId((long) ProductStatusConstance.available);

        Product product = new Product();
        product.setId(1L);
        product.setStockQty(3);
        product.setProductStatus(availableStatus);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        ProductOutOfStockException exception = assertThrows(
                ProductOutOfStockException.class,
                () -> calculationStockService.calcProductStockQty(1L, 5)
        );

        assertEquals("Product with id 1 has not enough stock. Requested: 5, available: 3", exception.getMessage());
    }
}
