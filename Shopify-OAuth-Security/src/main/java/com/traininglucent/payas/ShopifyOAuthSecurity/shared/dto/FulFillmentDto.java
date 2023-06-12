package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.FulFillmentOrderRequest;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.TrackingInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FulFillmentDto {
    private Long id;
    private Long order_id;
    private String status;
    private Long location_id;
    private List<LineItemsFulFillmentDto> line_items;
    private String name;
}
