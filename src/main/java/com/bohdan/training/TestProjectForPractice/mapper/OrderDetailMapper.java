package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.Request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.Response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import com.bohdan.training.TestProjectForPractice.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {OrderMapper.class, ProductMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

    @Mapping(target = "order", source = "order")
    @Mapping(target = "product", source = "product")
    OrderDetailDto toDto(OrderDetail orderDetail);

    @Mapping(target = "order", source = "orderId", qualifiedByName = "toOrder")
    @Mapping(target = "product", source = "productId", qualifiedByName = "toProduct")
    OrderDetail toEntity(OrderDetailUpsertDto dto);

    List<OrderDetailDto> toDtoList(List<OrderDetail> orderDetails);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", source = "orderId", qualifiedByName = "toOrder")
    @Mapping(target = "product", source = "productId", qualifiedByName = "toProduct")
    void updateOrderDetailFromDto(OrderDetailUpsertDto dto, @MappingTarget OrderDetail entity);

    @Named("toOrder")
    default Order toOrder(Long orderId) {
        if (orderId == null) return null;
        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    @Named("toProduct")
    default Product toProduct(Long productId) {
        if (productId == null) return null;
        Product product = new Product();
        product.setId(productId);
        return product;
    }
}
