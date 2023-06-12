package com.traininglucent.payas.ShopifyOAuthSecurity.controller;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.OrderRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.OrderRest;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.OrderService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.OrderDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/v1/create/order")
    public ResponseEntity<OrderRest> createOrder(@RequestBody OrderRequestModel orderRequest){

        ModelMapper modelMapper = new ModelMapper();
        OrderDto orderDto = modelMapper.map(orderRequest, OrderDto.class);

        OrderDto createdOrder = orderService.createOrder(orderDto);

        OrderRest returnBody = modelMapper.map(createdOrder, OrderRest.class);

        return ResponseEntity.ok()
                .body(returnBody);
    }

    @GetMapping("/v1/view/orders/all")
    public ResponseEntity<List<OrderRest>> getAllOrders(){
        List<OrderDto> allOrders = orderService.getAllOrders();

        ModelMapper modelMapper = new ModelMapper();
        List<OrderRest> orders = modelMapper.map(allOrders, List.class);

        return ResponseEntity.ok()
                .body(orders);
    }

}
