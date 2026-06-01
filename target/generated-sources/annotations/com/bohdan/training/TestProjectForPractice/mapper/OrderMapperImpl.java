package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.request.OrderUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDto;
import com.bohdan.training.TestProjectForPractice.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-01T19:29:50+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setClient( clientMapper.toDto( order.getClient() ) );
        orderDto.setId( order.getId() );
        orderDto.setDate( order.getDate() );
        orderDto.setSum( order.getSum() );

        return orderDto;
    }

    @Override
    public Order toEntity(OrderUpsertDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setClient( toClient( dto.getClientId() ) );

        return order;
    }

    @Override
    public List<OrderDto> toDtoList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    @Override
    public void updateOrderFromDto(OrderUpsertDto dto, Order entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getClientId() != null ) {
            entity.setClient( toClient( dto.getClientId() ) );
        }
    }
}
