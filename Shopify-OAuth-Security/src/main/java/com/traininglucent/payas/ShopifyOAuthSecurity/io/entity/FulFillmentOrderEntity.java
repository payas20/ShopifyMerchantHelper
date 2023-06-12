package com.traininglucent.payas.ShopifyOAuthSecurity.io.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fulfillment_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FulFillmentOrderEntity {
    @Id
    private Long id;
    private Long shop_id;
    private Long order_id;
    private Long assigned_location_id;
    private String request_status;
    private String status;
//    private ShippingAddressEntity destination;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fulfillment_order_id")
    private List<LineItemsFulFillmentEntity> line_items;
}
