package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductDto> getAll(Pageable pageable);

    ProductDto getById(Long id);

    IdDto create(ProductUpsertDto dto);

    void update(Long id, ProductUpsertDto updatedDto);

    void delete(Long id);
}