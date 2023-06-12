package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionRequestModel {
    private String name;
    private List<String> values;
}
