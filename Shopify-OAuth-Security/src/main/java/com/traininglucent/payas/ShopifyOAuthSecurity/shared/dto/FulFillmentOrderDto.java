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
public class FulFillmentOrderDto {
    private Long id;
    private Long shop_id;
    private Long order_id;
    private Long assigned_location_id;
    private String request_status;
    private String status;
    private AddressDto destination;
    private List<LineItemsFulFillmentOrderDto> line_items;

}
