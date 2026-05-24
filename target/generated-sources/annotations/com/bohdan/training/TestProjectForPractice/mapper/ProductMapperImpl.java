package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.ProductCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.ProductResponseDto;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T17:29:59+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public ProductResponseDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setBrand( brandMapper.toDto( product.getBrand() ) );
        productResponseDto.setId( product.getId() );
        productResponseDto.setName( product.getName() );
        productResponseDto.setCost( product.getCost() );
        productResponseDto.setPrice( product.getPrice() );
        productResponseDto.setDescription( product.getDescription() );

        return productResponseDto;
    }

    @Override
    public Product toEntity(ProductCreateUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setBrand( toBrand( dto.getBrandId() ) );
        product.setName( dto.getName() );
        product.setCost( dto.getCost() );
        product.setPrice( dto.getPrice() );
        product.setDescription( dto.getDescription() );

        return product;
    }

    @Override
    public List<ProductResponseDto> toDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponseDto> list = new ArrayList<ProductResponseDto>( products.size() );
        for ( Product product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public void updateProductFromDto(ProductCreateUpdateDto dto, Product entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getBrandId() != null ) {
            entity.setBrand( toBrand( dto.getBrandId() ) );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getCost() != null ) {
            entity.setCost( dto.getCost() );
        }
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
        if ( dto.getDescription() != null ) {
            entity.setDescription( dto.getDescription() );
        }
    }
}
