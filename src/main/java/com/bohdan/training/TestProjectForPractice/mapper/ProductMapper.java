package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.request.ProductUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.ProductDto;
import com.bohdan.training.TestProjectForPractice.entity.Brand;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import com.bohdan.training.TestProjectForPractice.entity.ProductStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, ProductStatusMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "productStatus", source = "productStatus")
    ProductDto toDto(Product product);

    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrand")
    @Mapping(target = "productStatus", source = "productStatusId", qualifiedByName = "toProductStatus")
    Product toEntity(ProductUpsertDto dto);

    List<ProductDto> toDtoList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", source = "brandId", qualifiedByName = "toBrand")
    @Mapping(target = "productStatus", source = "productStatusId", qualifiedByName = "toProductStatus")
    void updateProductFromDto(ProductUpsertDto dto, @MappingTarget Product entity);

    @Named("toBrand")
    default Brand toBrand(Long brandId) {
        if (brandId == null) return null;
        Brand brand = new Brand();
        brand.setId(brandId);
        return brand;
    }

    @Named("toProductStatus")
    default ProductStatus toProductStatus(Long productStatusId) {
        if (productStatusId == null) return null;

        ProductStatus productStatus = new ProductStatus();
        productStatus.setId(productStatusId);
        return productStatus;
    }
}
