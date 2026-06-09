package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.request.OrderDetailUpsertDto;
import com.bohdan.training.TestProjectForPractice.dto.response.OrderDetailDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-09T22:34:29+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public OrderDetailDto toDto(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDto orderDetailDto = new OrderDetailDto();

        orderDetailDto.setOrder( orderMapper.toDto( orderDetail.getOrder() ) );
        orderDetailDto.setProduct( productMapper.toDto( orderDetail.getProduct() ) );
        orderDetailDto.setId( orderDetail.getId() );
        orderDetailDto.setQty( orderDetail.getQty() );
        orderDetailDto.setPrice( orderDetail.getPrice() );

        return orderDetailDto;
    }

    @Override
    public OrderDetail toEntity(OrderDetailUpsertDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrder( toOrder( dto.getOrderId() ) );
        orderDetail.setProduct( toProduct( dto.getProductId() ) );
        orderDetail.setQty( dto.getQty() );

        return orderDetail;
    }

    @Override
    public List<OrderDetailDto> toDtoList(List<OrderDetail> orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        List<OrderDetailDto> list = new ArrayList<OrderDetailDto>( orderDetails.size() );
        for ( OrderDetail orderDetail : orderDetails ) {
            list.add( toDto( orderDetail ) );
        }

        return list;
    }

    @Override
    public void updateOrderDetailFromDto(OrderDetailUpsertDto dto, OrderDetail entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getOrderId() != null ) {
            entity.setOrder( toOrder( dto.getOrderId() ) );
        }
        if ( dto.getProductId() != null ) {
            entity.setProduct( toProduct( dto.getProductId() ) );
        }
        if ( dto.getQty() != null ) {
            entity.setQty( dto.getQty() );
        }
    }
}
