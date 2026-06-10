package com.bohdan.training.TestProjectForPractice.exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException(String message) {
        super(message);
    }

    public ProductOutOfStockException(Long productId, int requestedQty, int availableQty) {
        super("Product with id " + productId +
                " has not enough stock. Requested: " + requestedQty +
                ", available: " + availableQty);
    }
}
