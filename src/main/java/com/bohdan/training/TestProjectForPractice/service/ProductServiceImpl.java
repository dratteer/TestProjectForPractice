package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.Request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.Response.ProductDto;
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
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();

        return productMapper.toDtoList(products);
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toDto(product);
    }

    @Override
    public IdDto create(ProductUpsertDto dto) {                      //public ProductDto create(ProductDto dto) {
        Product entity = productMapper.toEntity(dto);
        Product saved = productRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, ProductUpsertDto updatedDto) {
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