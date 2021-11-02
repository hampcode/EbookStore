package com.example.m3.controllers;

import com.example.m3.converters.OrderConverter;
import com.example.m3.dto.OrderDto;
import com.example.m3.entities.Order;
import com.example.m3.service.OrderService;
import com.example.m3.utils.WrapperResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderConverter converter;

    public OrderController(OrderService orderService, OrderConverter converter) {
        this.orderService = orderService;
        this.converter = converter;
    }

    @ApiOperation(value = "Retrieve all existed orders", notes = "This Operation returns all stored orders.")
    @GetMapping
    public ResponseEntity<WrapperResponse<List<OrderDto>>> findAll() {
        List<Order> orders = orderService.findAllOrders();
        return new WrapperResponse<>(true, "success", converter.fromEntity(orders))
                .createResponse();
    }

    @ApiOperation(value = "Retrieve an order based on Id ", notes = "This Operation returns an orders using its Id")
    @GetMapping(value = "/{orderId}")
    public  ResponseEntity<WrapperResponse<OrderDto>> findById(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        return new WrapperResponse<>(true, "success", converter.fromEntity(order))
                .createResponse();
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<OrderDto>> create(@RequestBody OrderDto order){
        Order newOrder = orderService.createOrder(converter.fromDTO(order));
        return new WrapperResponse<>(true, "success", converter.fromEntity(newOrder))
                .createResponse();
    }
}
