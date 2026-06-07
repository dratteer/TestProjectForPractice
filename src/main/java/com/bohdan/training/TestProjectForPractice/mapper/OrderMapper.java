package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.request.OrderUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDto;
import com.bohdan.training.TestProjectForPractice.entity.Client;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import com.bohdan.training.TestProjectForPractice.entity.OrderStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {ClientMapper.class, OrderStatusMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "client", source = "client")
    @Mapping(target = "orderStatus", source = "orderStatus")
    OrderDto toDto(Order order);

    @Mapping(target = "client", source = "clientId", qualifiedByName = "toClient")
    @Mapping(target = "orderStatus", source = "orderStatusId", qualifiedByName = "toOrderStatus")
    Order toEntity(OrderUpsertDto dto);

    List<OrderDto> toDtoList(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", source = "clientId", qualifiedByName = "toClient")
    @Mapping(target = "orderStatus", source = "orderStatusId", qualifiedByName = "toOrderStatus")
    void updateOrderFromDto(OrderUpsertDto dto, @MappingTarget Order entity);

    @Named("toClient")
    default Client toClient(Long clientId) {
        if (clientId == null) return null;
        Client client = new Client();
        client.setId(clientId);
        return client;
    }

    @Named("toOrderStatus")
    default OrderStatus toOrderStatus(Long orderStatusId) {
        if (orderStatusId == null) return null;

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(orderStatusId);
        return orderStatus;
    }
}
