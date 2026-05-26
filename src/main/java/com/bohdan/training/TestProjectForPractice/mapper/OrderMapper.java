package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.Request.OrderUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.Response.OrderDto;
import com.bohdan.training.TestProjectForPractice.entity.Client;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {ClientMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "client", source = "client")
    OrderDto toDto(Order order);

    @Mapping(target = "client", source = "clientId", qualifiedByName = "toClient")
    Order toEntity(OrderUpsertDto dto);

    List<OrderDto> toDtoList(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", source = "clientId", qualifiedByName = "toClient")
    void updateOrderFromDto(OrderUpsertDto dto, @MappingTarget Order entity);

    @Named("toClient")
    default Client toClient(Long clientId) {
        if (clientId == null) return null;
        Client client = new Client();
        client.setId(clientId);
        return client;
    }
}
