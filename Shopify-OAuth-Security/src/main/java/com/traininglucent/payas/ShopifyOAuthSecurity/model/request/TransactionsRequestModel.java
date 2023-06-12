package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsRequestModel {
    private String kind;
    private String status;
    private Double amount;
}
