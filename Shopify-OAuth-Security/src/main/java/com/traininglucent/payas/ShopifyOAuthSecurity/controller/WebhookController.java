package com.traininglucent.payas.ShopifyOAuthSecurity.controller;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.WebhookRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.WebhookRest;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.WebhookService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.WebhookDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @Autowired
    WebhookService webhookService;

    @PostMapping("/v1/create/webhook")
    public ResponseEntity<WebhookRest> createWebhook(@RequestBody WebhookRequestModel webhookRequest){
        ModelMapper modelMapper = new ModelMapper();
        WebhookDto webhookDto = modelMapper.map(webhookRequest, WebhookDto.class);


        WebhookDto createdWebhook = webhookService.createWebhook(webhookDto);

        WebhookRest returnBody = modelMapper.map(createdWebhook, WebhookRest.class);

        return ResponseEntity.ok()
                .body(returnBody);
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> recieveWebhook(@RequestBody Object obj){
        System.out.printf(obj.toString());
        return ResponseEntity.ok()
                .body(obj);
    }
}
