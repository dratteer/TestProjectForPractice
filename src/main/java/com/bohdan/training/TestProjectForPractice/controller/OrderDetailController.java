package com.bohdan.training.TestProjectForPractice.controller;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.request.UpdateOrderDetailQtyDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.service.OrderDetailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping()
    public ResponseEntity<List<OrderDetailDto>> getAll() {
        return ResponseEntity.ok(orderDetailService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderDetailService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<IdDto> create(@RequestBody OrderDetailUpsertDto dto) {
        IdDto saved = orderDetailService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderDetailService.delete(id);
    }

    @PatchMapping("/{id}/qty")
    public ResponseEntity<Void> updateQty(@PathVariable Long id, @RequestBody @Valid UpdateOrderDetailQtyDto dto) {
        orderDetailService.updateQty(id, dto.getQty());
        return ResponseEntity.noContent().build();
    }
}