package com.traininglucent.payas.ShopifyOAuthSecurity.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebhookRest {
    private Long id;
    private String topic;
    private String address;
    private String created_at;
    private String updated_at;
    private String format;
    private List<String> fields;
    private String api_version;
}
