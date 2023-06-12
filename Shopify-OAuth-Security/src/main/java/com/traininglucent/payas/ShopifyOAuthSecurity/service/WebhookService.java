package com.traininglucent.payas.ShopifyOAuthSecurity.service;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.WebhookDto;

public interface WebhookService {
    WebhookDto createWebhook(WebhookDto webhookDto);
}
