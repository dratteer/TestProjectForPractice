package com.bohdan.training.TestProjectForPractice.controller;

import com.bohdan.training.TestProjectForPractice.dto.BrandDto;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping()
    public ResponseEntity<List<BrandDto>> getAll() {
        return ResponseEntity.ok(brandService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<IdDto> create(@RequestBody BrandDto dto) {
        IdDto saved = brandService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody BrandDto brandDto) {
        brandService.update(id, brandDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        brandService.delete(id);
    }
}
