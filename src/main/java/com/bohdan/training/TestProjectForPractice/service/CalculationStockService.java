package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.entity.Product;

public interface CalculationStockService {
    Product calcProductStockQty(Long productId, Integer qty);
    void returnProductStock(Long productId, Integer qty);
}
