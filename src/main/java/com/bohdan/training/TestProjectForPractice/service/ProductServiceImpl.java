package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.constance.ProductStatusConstance;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.dto.request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.ProductDto;
import com.bohdan.training.TestProjectForPractice.dto.response.SupplierApiDto;
import com.bohdan.training.TestProjectForPractice.entity.Brand;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.entity.ProductStatus;
import com.bohdan.training.TestProjectForPractice.exception.EntityNotFoundException;
import com.bohdan.training.TestProjectForPractice.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.bohdan.training.TestProjectForPractice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final RestClient restClient;

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
    @Transactional
    public void addSuppliersProducts() {
        ProductUpsertDto productUpsertDto = new ProductUpsertDto();

        List<SupplierApiDto> receivedDto = restClient.get()
                .uri("/supplier-products")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<SupplierApiDto>>() {});

        for (SupplierApiDto supplierProduct : receivedDto) {
            var product = productRepository.findBySupplierProductId(supplierProduct.getId());
            if (product == null) {
                Product newProduct = new Product();
                newProduct.setSupplierProductId(supplierProduct.getId());
                newProduct.setName(supplierProduct.getName());
                newProduct.setStockQty(supplierProduct.getQty());
                newProduct.setCost(supplierProduct.getCost());
                newProduct.setPrice(
                        supplierProduct.getCost().multiply(BigDecimal.valueOf(1.2)));
                Brand newBrand = new Brand();
                newBrand.setId(1L); //TODO magic number move to const
                newProduct.setBrand(newBrand);
                ProductStatus newStatus = new ProductStatus();
                newStatus.setId((long) ProductStatusConstance.comingSoon);
                newProduct.setProductStatus(newStatus);
                productRepository.save(newProduct);
            }
            else {
                product.setName(supplierProduct.getName());
                int statusId = product.getProductStatus().getId().intValue();

                if (statusId == ProductStatusConstance.discontinued){
                    ProductStatus newStatus = new ProductStatus();
                    newStatus.setId((long) ProductStatusConstance.comingSoon);
                    product.setProductStatus(newStatus);
                }
            }
        }

        /*productUpsertDto.setName(receivedDto.getName());
        productUpsertDto.setCost(receivedDto.getCost());
        productUpsertDto.setPrice(
                receivedDto.getCost().multiply(BigDecimal.valueOf(1.2)));*/

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