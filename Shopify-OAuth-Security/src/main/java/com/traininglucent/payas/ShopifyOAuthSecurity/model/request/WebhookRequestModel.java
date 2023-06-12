package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookRequestModel {
    private String topic;
    private String address;
    private String format;
    private List<String> fields;
}
