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
public class ProductRequestModel {
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private String status;
    private List<VariantRequestModel> variants;
//    private List<OptionRequestModel> options;
}
