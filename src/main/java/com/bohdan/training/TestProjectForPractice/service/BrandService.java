package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.Response.BrandDto;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;

import java.util.List;

public interface BrandService {
    List<BrandDto> getAll();

    BrandDto getById(Long id);

    IdDto create(BrandDto dto);

    void update(Long id, BrandDto updatedDto);

    void delete(Long id);
}
