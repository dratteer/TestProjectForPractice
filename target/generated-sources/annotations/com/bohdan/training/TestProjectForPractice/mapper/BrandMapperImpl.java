package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.BrandDto;
import com.bohdan.training.TestProjectForPractice.entity.Brand;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T17:29:59+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class BrandMapperImpl implements BrandMapper {

    @Override
    public BrandDto toDto(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandDto brandDto = new BrandDto();

        brandDto.setId( brand.getId() );
        brandDto.setName( brand.getName() );

        return brandDto;
    }

    @Override
    public Brand toEntity(BrandDto brandDto) {
        if ( brandDto == null ) {
            return null;
        }

        Brand brand = new Brand();

        brand.setId( brandDto.getId() );
        brand.setName( brandDto.getName() );

        return brand;
    }

    @Override
    public List<BrandDto> toDtoList(List<Brand> brands) {
        if ( brands == null ) {
            return null;
        }

        List<BrandDto> list = new ArrayList<BrandDto>( brands.size() );
        for ( Brand brand : brands ) {
            list.add( toDto( brand ) );
        }

        return list;
    }

    @Override
    public void updateBrandFromDto(BrandDto dto, Brand entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
    }
}
