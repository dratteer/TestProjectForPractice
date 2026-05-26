package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.Response.BrandDto;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.entity.Brand;
import com.bohdan.training.TestProjectForPractice.mapper.BrandMapper;
import com.bohdan.training.TestProjectForPractice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandDto> getAll() {
        List<Brand> brands = brandRepository.findAll();

        return brandMapper.toDtoList(brands);
    }

    @Override
    public BrandDto getById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return brandMapper.toDto(brand);
    }

    @Override
    public IdDto create(BrandDto dto) {
        Brand entity = brandMapper.toEntity(dto);
        Brand saved = brandRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, BrandDto updatedDto) {
        Brand existing = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        brandMapper.updateBrandFromDto(updatedDto, existing);
        brandRepository.save(existing);
    }

    @Override
    public  void delete(Long id) {
        brandRepository.deleteById(id);
    }
}
