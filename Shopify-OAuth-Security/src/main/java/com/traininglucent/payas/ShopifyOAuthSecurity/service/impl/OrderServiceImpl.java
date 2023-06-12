package com.traininglucent.payas.ShopifyOAuthSecurity.service.impl;

import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Order;
import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Product;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.OrderEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.OrderRepository;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ShopifyAdminAccessTokenRepo;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.OrderService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.OrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ShopifyAdminAccessTokenRepo accessTokenRepo;

    @Autowired
    OrderRepository orderRepository;

    private final String shop = "training-store-lucent.myshopify.com";

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        ModelMapper modelMapper = new ModelMapper();
        Order order = modelMapper.map(orderDto, Order.class);
        System.out.println(order);
        Order createdOrder = webClient.post()
                .uri("https://" + shop + "/admin/api/2023-04/orders.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .body(Mono.just(order), Order.class)
                .retrieve()
                .bodyToMono(Order.class).block();

        OrderEntity savedOrder = modelMapper.map(createdOrder.getOrder(), OrderEntity.class);
        orderRepository.save(savedOrder);


        return createdOrder.getOrder();
    }

    @Override
    public List<OrderDto> getAllOrders() {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        HashMap<String, List> allOrders= webClient.get()
                .uri("https://" + shop + "/admin/api/2023-04/orders.json?status=any")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(HashMap.class).block();

        List<OrderDto> orders = allOrders.get("orders");

        return orders;
    }
}
