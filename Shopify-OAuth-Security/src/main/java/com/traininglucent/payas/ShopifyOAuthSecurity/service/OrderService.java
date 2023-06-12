package com.traininglucent.payas.ShopifyOAuthSecurity.service;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();
}
