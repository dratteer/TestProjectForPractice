package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.OrderCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderResponseDto;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-24T17:29:58+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public OrderResponseDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setClient( clientMapper.toDto( order.getClient() ) );
        orderResponseDto.setId( order.getId() );
        orderResponseDto.setDate( order.getDate() );
        orderResponseDto.setSum( order.getSum() );

        return orderResponseDto;
    }

    @Override
    public Order toEntity(OrderCreateUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setClient( toClient( dto.getClientId() ) );
        order.setId( dto.getId() );
        order.setDate( dto.getDate() );
        order.setSum( dto.getSum() );

        return order;
    }

    @Override
    public List<OrderResponseDto> toDtoList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponseDto> list = new ArrayList<OrderResponseDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    @Override
    public void updateOrderFromDto(OrderCreateUpdateDto dto, Order entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getClientId() != null ) {
            entity.setClient( toClient( dto.getClientId() ) );
        }
        if ( dto.getDate() != null ) {
            entity.setDate( dto.getDate() );
        }
        if ( dto.getSum() != null ) {
            entity.setSum( dto.getSum() );
        }
    }
}
