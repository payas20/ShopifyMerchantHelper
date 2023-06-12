package com.traininglucent.payas.ShopifyOAuthSecurity.helper;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentOrderDto;
import lombok.Data;

import java.util.List;

@Data
public class FulfillmentOrders {
    private List<FulFillmentOrderDto> fulfillment_orders;
}
