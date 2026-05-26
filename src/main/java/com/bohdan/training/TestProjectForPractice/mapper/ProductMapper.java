package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.Request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.Response.ProductDto;
import com.bohdan.training.TestProjectForPractice.entity.Brand;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BrandMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "brand", source = "brand")
    ProductDto toDto(Product product);

    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrand")
    Product toEntity(ProductUpsertDto dto);

    List<ProductDto> toDtoList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrand")
    void updateProductFromDto(ProductUpsertDto dto, @MappingTarget Product entity);

    @Named("toBrand")
    default Brand toBrand(Long brandId) {
        if (brandId == null) return null;
        Brand brand = new Brand();
        brand.setId(brandId);
        return brand;
    }
}
