package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.response.ProductStatusDto;
import com.bohdan.training.TestProjectForPractice.entity.ProductStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-10T21:22:55+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class ProductStatusMapperImpl implements ProductStatusMapper {

    @Override
    public ProductStatusDto toDto(ProductStatus productStatus) {
        if ( productStatus == null ) {
            return null;
        }

        ProductStatusDto productStatusDto = new ProductStatusDto();

        productStatusDto.setId( productStatus.getId() );
        productStatusDto.setName( productStatus.getName() );

        return productStatusDto;
    }

    @Override
    public ProductStatus toEntity(ProductStatusDto productStatusDto) {
        if ( productStatusDto == null ) {
            return null;
        }

        ProductStatus productStatus = new ProductStatus();

        productStatus.setId( productStatusDto.getId() );
        productStatus.setName( productStatusDto.getName() );

        return productStatus;
    }

    @Override
    public List<ProductStatusDto> toDtoList(List<ProductStatus> productStatuses) {
        if ( productStatuses == null ) {
            return null;
        }

        List<ProductStatusDto> list = new ArrayList<ProductStatusDto>( productStatuses.size() );
        for ( ProductStatus productStatus : productStatuses ) {
            list.add( toDto( productStatus ) );
        }

        return list;
    }

    @Override
    public void updateProductStatusFromDto(ProductStatusDto dto, ProductStatus entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
    }
}
