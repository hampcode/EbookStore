package com.example.m3.converters;


import com.example.m3.dto.OrderDto;
import com.example.m3.dto.OrderLineDto;
import com.example.m3.entities.Order;
import com.example.m3.entities.OrderDetail;
import lombok.AllArgsConstructor;

import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;




@AllArgsConstructor
public class OrderConverter extends AbstractConverter<Order, OrderDto> {

    private BookConverter bookConverter;
    private UserConverter userConverter;

    @Override
    public OrderDto fromEntity(Order entity) {
        if (entity == null)
            return null;

        List<OrderLineDto> items = fromOrderLineEntity(entity.getItems());

        return OrderDto.builder()
                .id(entity.getId())
                .lines(items)
                .dateCreate(entity.getDateCreated())
                .user(userConverter.fromEntity(entity.getUser()))
                .total(entity.getTotal())
                .build();
    }

    @Override
    public Order fromDTO(OrderDto dto) {
        if (dto == null) return null;

        List<OrderDetail> items = fromOrderLineDTO(dto.getLines());

        return Order.builder()
                .id(dto.getId())
                .items(items)
                .total(dto.getTotal())
                .user(userConverter.fromDTO(dto.getUser()))
                .build();
    }

    private List<OrderLineDto> fromOrderLineEntity(List<OrderDetail> items) {
        if(items == null) return null;

        return items.stream().map(line -> {
                    return OrderLineDto.builder()
                            .id(line.getId())
                            .price(line.getPrice())
                            .book(bookConverter.fromEntity(line.getBook()))
                            .quantity(line.getQuantity())
                            .total(line.getTotal())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private List<OrderDetail> fromOrderLineDTO(List<OrderLineDto> lines) {
        if(lines == null) return null;

        return lines.stream().map(line -> {
                    return OrderDetail.builder()
                            .id(line.getId())
                            .price(line.getPrice())
                            .book(bookConverter.fromDTO(line.getBook()))
                            .quantity(line.getQuantity())
                            .total(line.getTotal())
                            .build();
                })
                .collect(Collectors.toList());
    }

}