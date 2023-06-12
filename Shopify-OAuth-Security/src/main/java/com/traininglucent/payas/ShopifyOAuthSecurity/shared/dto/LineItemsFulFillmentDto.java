package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineItemsFulFillmentDto {
    private Long id;
    private Integer quantity;
    private Integer fulfillable_quantity;
    private Long variant_id;
    private Long product_id;
    private String title;
    private String requires_shipping;
    private String fulfillment_status;
}
