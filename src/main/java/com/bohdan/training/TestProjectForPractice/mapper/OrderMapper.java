package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.OrderCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderResponseDto;
import com.bohdan.training.TestProjectForPractice.entity.Client;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {ClientMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "client", source = "client")
    OrderResponseDto toDto(Order order);

    @Mapping(target = "client", source = "clientId", qualifiedByName = "toClient")
    Order toEntity(OrderCreateUpdateDto dto);

    List<OrderResponseDto> toDtoList(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", source = "clientId", qualifiedByName = "toClient")
    void updateOrderFromDto(OrderCreateUpdateDto dto, @MappingTarget Order entity);

    @Named("toClient")
    default Client toClient(Long clientId) {
        if (clientId == null) return null;
        Client client = new Client();
        client.setId(clientId);
        return client;
    }
}
