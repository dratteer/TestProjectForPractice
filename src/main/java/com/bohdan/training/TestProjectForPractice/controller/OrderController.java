package com.bohdan.training.TestProjectForPractice.controller;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderResponseDto;
import com.bohdan.training.TestProjectForPractice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<IdDto> create(@RequestBody OrderCreateUpdateDto dto) {
        IdDto saved = orderService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody OrderCreateUpdateDto orderDtoResponse) {
        orderService.update(id, orderDtoResponse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
