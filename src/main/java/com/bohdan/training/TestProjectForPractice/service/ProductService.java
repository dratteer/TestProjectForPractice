package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.Request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.Response.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();

    ProductDto getById(Long id);

    IdDto create(ProductUpsertDto dto);

    void update(Long id, ProductUpsertDto updatedDto);

    void delete(Long id);
}