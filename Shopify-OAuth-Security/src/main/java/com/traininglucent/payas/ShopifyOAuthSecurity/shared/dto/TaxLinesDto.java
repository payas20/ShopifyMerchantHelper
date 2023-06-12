package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxLinesDto {
    private Double price;
    private Double rate;
    private String title;
}
