package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FulFillmentRequestModel {
    private List<FulFillmentOrderRequest> line_items_by_fulfillment_order;
    private TrackingInfo tracking_info;
}
