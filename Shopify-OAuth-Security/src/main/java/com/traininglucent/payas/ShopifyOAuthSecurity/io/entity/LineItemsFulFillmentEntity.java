package com.traininglucent.payas.ShopifyOAuthSecurity.io.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fulfillment_orders_line_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemsFulFillmentEntity {
    @Id
    private Long id;
    private Long shop_id;
    private Integer quantity;
    private Long line_item_id;
    private Long inventory_item_id;
    private Integer fulfillable_quantity;
    private Long variant_id;
}
