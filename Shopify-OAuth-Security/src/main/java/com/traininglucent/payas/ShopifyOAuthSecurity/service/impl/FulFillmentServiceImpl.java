package com.traininglucent.payas.ShopifyOAuthSecurity.service.impl;

import com.traininglucent.payas.ShopifyOAuthSecurity.helper.FulFillment;
import com.traininglucent.payas.ShopifyOAuthSecurity.helper.FulFillmentResponse;
import com.traininglucent.payas.ShopifyOAuthSecurity.helper.FulfillmentOrders;
import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Product;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.FulFillmentOrderEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.FulFillmentRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.FulFillmentOrderRepository;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ShopifyAdminAccessTokenRepo;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.FulFillmentService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentDto;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentOrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Service
public class FulFillmentServiceImpl implements FulFillmentService {

    @Autowired
    ShopifyAdminAccessTokenRepo accessTokenRepo;

    @Autowired
    FulFillmentOrderRepository fulFillmentOrderRepository;

    private final String shop = "training-store-lucent.myshopify.com";

    @Override
    public List<FulFillmentOrderDto> getFullFillmentOrders(Long order_id) {


        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        FulfillmentOrders fulfillmentOrders = webClient.get()
                .uri("https://" + shop + "/admin/api/2023-04/orders/" + order_id + "/fulfillment_orders.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(FulfillmentOrders.class).block();

        List<FulFillmentOrderDto> fulFillmentOrderDtoList = fulfillmentOrders.getFulfillment_orders();

        ModelMapper modelMapper = new ModelMapper();

        for (int i=0;i< fulFillmentOrderDtoList.size();i++){
            FulFillmentOrderDto obj = fulFillmentOrderDtoList.get(i);
            FulFillmentOrderEntity saved_fulfillment_order = modelMapper.map(obj, FulFillmentOrderEntity.class);
            fulFillmentOrderRepository.save(saved_fulfillment_order);
        }

        return fulFillmentOrderDtoList;
    }

    @Override
    public FulFillmentDto createFulFillments(FulFillmentRequestModel fulFillmentRequest) {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        FulFillment fulfillment = new FulFillment(fulFillmentRequest);

        WebClient webClient = WebClient.create();

        FulFillmentResponse createdFulFillment = webClient.post()
                .uri("https://" + shop + "/admin/api/2023-04/fulfillments.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .body(Mono.just(fulfillment), FulFillment.class)
                .retrieve()
                .bodyToMono(FulFillmentResponse.class).block();

        return createdFulFillment.getFulfillment();
    }
}
