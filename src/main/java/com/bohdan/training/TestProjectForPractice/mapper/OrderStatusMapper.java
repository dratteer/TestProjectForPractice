package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.response.OrderStatusDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
    OrderStatusDto toDto(OrderStatus orderStatus);
    OrderStatus toEntity(OrderStatusDto orderStatusDto);
    List<OrderStatusDto> toDtoList(List<OrderStatus> orderStatuses);

    @Mapping(target = "id", ignore = true)
    void updateOrderStatusFromDto(OrderStatusDto dto, @MappingTarget OrderStatus entity);
}
