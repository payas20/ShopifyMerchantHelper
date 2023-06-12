package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private String status;
    private List<VariantDto> variants;
    private List<OptionDto> options;
}
