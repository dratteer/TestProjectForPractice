package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.constance.ProductStatusConstance;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.entity.ProductStatus;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
import com.bohdan.training.TestProjectForPractice.repository.ProductStatusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationStockServiceImpl implements CalculationStockService {
    private final ProductRepository productRepository;
    private final ProductStatusRepository productStatusRepository;

    @Override
    @Transactional
    public Product calcProductStockQty(Long productId, Integer qty) {
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
        return product;
    }

    @Override
    @Transactional
    public Product returnProductStock(Long productId, Integer qty) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int newQty = product.getStockQty() + qty;

        product.setStockQty(newQty);

        int statusId = product.getProductStatus().getId().intValue();

        if (statusId != ProductStatusConstance.discontinued
                && statusId != ProductStatusConstance.comingSoon
                && statusId != ProductStatusConstance.available) {

            ProductStatus available = productStatusRepository.findById(
                            (long) ProductStatusConstance.available)
                    .orElseThrow(() -> new RuntimeException("ProductStatus not found"));

            product.setProductStatus(available);
        }

        return product;
    }
}
