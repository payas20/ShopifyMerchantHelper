package com.traininglucent.payas.ShopifyOAuthSecurity.service.impl;

import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Webhook;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ShopifyAdminAccessTokenRepo;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.WebhookService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.WebhookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebhookServiceImpl implements WebhookService {

    @Autowired
    ShopifyAdminAccessTokenRepo accessTokenRepo;

    private final String shop = "training-store-lucent.myshopify.com";

    @Override
    public WebhookDto createWebhook(WebhookDto webhookDto) {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        Webhook webhook = new Webhook(webhookDto);

        WebClient webClient = WebClient.create();

        Webhook createdWebhook = webClient.post()
                .uri("https://" + shop + "/admin/api/2023-04/webhooks.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .body(Mono.just(webhook), Webhook.class)
                .retrieve()
                .bodyToMono(Webhook.class).block();

        return createdWebhook.getWebhook();
    }
}
