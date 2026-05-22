package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.ProductCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAll();

    ProductResponseDto getById(Long id);

    IdDto create(ProductCreateUpdateDto dto);

    void update(Long id, ProductCreateUpdateDto updatedDto);

    void delete(Long id);
}