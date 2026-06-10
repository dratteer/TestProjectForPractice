package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.entity.Product;

public interface CalculationStockService {
    Product calcProductStockQty(Long productId, Integer qty);
    Product returnProductStock(Long productId, Integer qty);
}
