package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.ProductDto;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.exception.EntityNotFoundException;
import com.bohdan.training.TestProjectForPractice.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductDto> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public ProductDto getById(Long id) {
        System.out.println("Loading product from DB: " + id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));

        return productMapper.toDto(product);
    }

    @Override
    public IdDto create(ProductUpsertDto dto) {
        Product entity = productMapper.toEntity(dto);
        Product saved = productRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void update(Long id, ProductUpsertDto updatedDto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product", id));

        productMapper.updateProductFromDto(updatedDto, existing);
        productRepository.save(existing);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public  void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product", id);
        }
        productRepository.deleteById(id);
    }
}