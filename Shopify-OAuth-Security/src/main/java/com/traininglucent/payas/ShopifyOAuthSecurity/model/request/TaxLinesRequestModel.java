package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxLinesRequestModel {
    private Double price;
    private Double rate;
    private String title;
}
