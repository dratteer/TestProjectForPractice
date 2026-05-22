package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.BrandDto;
import com.bohdan.training.TestProjectForPractice.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandDto toDto(Brand brand);
    Brand toEntity(BrandDto brandDto);
    List<BrandDto> toDtoList(List<Brand> brands);

    @Mapping(target = "id", ignore = true)
    void updateBrandFromDto(BrandDto dto, @MappingTarget Brand entity);
}
