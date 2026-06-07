package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.response.ProductStatusDto;
import com.bohdan.training.TestProjectForPractice.entity.ProductStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductStatusMapper {
    ProductStatusDto toDto(ProductStatus productStatus);
    ProductStatus toEntity(ProductStatusDto productStatusDto);
    List<ProductStatusDto> toDtoList(List<ProductStatus> productStatuses);

    @Mapping(target = "id", ignore = true)
    void updateProductStatusFromDto(ProductStatusDto dto, @MappingTarget ProductStatus entity);
}
