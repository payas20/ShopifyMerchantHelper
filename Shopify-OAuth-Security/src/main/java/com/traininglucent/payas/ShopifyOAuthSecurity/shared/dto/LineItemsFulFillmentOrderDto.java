package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemsFulFillmentOrderDto {
    private Long id;
    private Long shop_id;
    private Long fulfillment_order_id;
    private Integer quantity;
    private Long line_item_id;
    private Long inventory_item_id;
    private Integer fulfillable_quantity;
    private Long variant_id;
}
