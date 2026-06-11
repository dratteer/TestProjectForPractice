package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.response.OrderStatusDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-11T13:53:29+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class OrderStatusMapperImpl implements OrderStatusMapper {

    @Override
    public OrderStatusDto toDto(OrderStatus orderStatus) {
        if ( orderStatus == null ) {
            return null;
        }

        OrderStatusDto orderStatusDto = new OrderStatusDto();

        orderStatusDto.setId( orderStatus.getId() );
        orderStatusDto.setName( orderStatus.getName() );

        return orderStatusDto;
    }

    @Override
    public OrderStatus toEntity(OrderStatusDto orderStatusDto) {
        if ( orderStatusDto == null ) {
            return null;
        }

        OrderStatus orderStatus = new OrderStatus();

        orderStatus.setId( orderStatusDto.getId() );
        orderStatus.setName( orderStatusDto.getName() );

        return orderStatus;
    }

    @Override
    public List<OrderStatusDto> toDtoList(List<OrderStatus> orderStatuses) {
        if ( orderStatuses == null ) {
            return null;
        }

        List<OrderStatusDto> list = new ArrayList<OrderStatusDto>( orderStatuses.size() );
        for ( OrderStatus orderStatus : orderStatuses ) {
            list.add( toDto( orderStatus ) );
        }

        return list;
    }

    @Override
    public void updateOrderStatusFromDto(OrderStatusDto dto, OrderStatus entity) {
        if ( dto == null ) {
            return;
        }

        entity.setName( dto.getName() );
    }
}
