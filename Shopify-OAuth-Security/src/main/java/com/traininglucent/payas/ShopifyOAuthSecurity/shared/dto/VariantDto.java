package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantDto {
    private Long id;
    private Long product_id;
    private String title;
    private String price;
    private String sku;
    private Integer position;
    private String option1;
    private String option2;
    private String option3;
}
