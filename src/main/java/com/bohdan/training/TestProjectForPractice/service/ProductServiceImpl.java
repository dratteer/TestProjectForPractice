package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.ProductCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.ProductResponseDto;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> getAll() {
        List<Product> products = productRepository.findAll();

        return productMapper.toDtoList(products);
    }

    @Override
    public ProductResponseDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toDto(product);
    }

    @Override
    public IdDto create(ProductCreateUpdateDto dto) {                      //public ProductResponseDto create(ProductResponseDto dto) {
        Product entity = productMapper.toEntity(dto);
        Product saved = productRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, ProductCreateUpdateDto updatedDto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productMapper.updateProductFromDto(updatedDto, existing);
        productRepository.save(existing);
    }

    @Override
    public  void delete(Long id) {
        productRepository.deleteById(id);
    }
}