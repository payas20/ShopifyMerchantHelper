package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantRequestModel {
    private String option1;
    private String option2;
    private String option3;
    private String price;
    private String sku;

}
