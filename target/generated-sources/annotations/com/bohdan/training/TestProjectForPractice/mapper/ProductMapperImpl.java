package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.ProductDto;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-01T19:29:50+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setBrand( brandMapper.toDto( product.getBrand() ) );
        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setCost( product.getCost() );
        productDto.setPrice( product.getPrice() );
        productDto.setDescription( product.getDescription() );
        productDto.setStockQty( product.getStockQty() );

        return productDto;
    }

    @Override
    public Product toEntity(ProductUpsertDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setBrand( toBrand( dto.getBrandId() ) );
        product.setName( dto.getName() );
        product.setCost( dto.getCost() );
        product.setPrice( dto.getPrice() );
        product.setDescription( dto.getDescription() );
        product.setStockQty( dto.getStockQty() );

        return product;
    }

    @Override
    public List<ProductDto> toDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }

    @Override
    public void updateProductFromDto(ProductUpsertDto dto, Product entity) {
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
        if ( dto.getStockQty() != null ) {
            entity.setStockQty( dto.getStockQty() );
        }
    }
}
