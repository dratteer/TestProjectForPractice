package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.OrderDetailCreateUpdateDto;
import com.bohdan.training.TestProjectForPractice.dto.OrderDetailResponseDto;
import com.bohdan.training.TestProjectForPractice.entity.OrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-22T16:27:51+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public OrderDetailResponseDto toDto(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailResponseDto orderDetailResponseDto = new OrderDetailResponseDto();

        orderDetailResponseDto.setOrder( orderMapper.toDto( orderDetail.getOrder() ) );
        orderDetailResponseDto.setProduct( productMapper.toDto( orderDetail.getProduct() ) );
        orderDetailResponseDto.setId( orderDetail.getId() );
        orderDetailResponseDto.setQty( orderDetail.getQty() );
        orderDetailResponseDto.setPrice( orderDetail.getPrice() );

        return orderDetailResponseDto;
    }

    @Override
    public OrderDetail toEntity(OrderDetailCreateUpdateDto dto) {
        if ( dto == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrder( toOrder( dto.getOrderId() ) );
        orderDetail.setProduct( toProduct( dto.getProductId() ) );
        orderDetail.setId( dto.getId() );
        orderDetail.setQty( dto.getQty() );
        orderDetail.setPrice( dto.getPrice() );

        return orderDetail;
    }

    @Override
    public List<OrderDetailResponseDto> toDtoList(List<OrderDetail> orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        List<OrderDetailResponseDto> list = new ArrayList<OrderDetailResponseDto>( orderDetails.size() );
        for ( OrderDetail orderDetail : orderDetails ) {
            list.add( toDto( orderDetail ) );
        }

        return list;
    }

    @Override
    public void updateOrderDetailFromDto(OrderDetailCreateUpdateDto dto, OrderDetail entity) {
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
        if ( dto.getPrice() != null ) {
            entity.setPrice( dto.getPrice() );
        }
    }
}
