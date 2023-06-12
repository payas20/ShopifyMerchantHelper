package com.traininglucent.payas.ShopifyOAuthSecurity.service;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.FulFillmentRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentDto;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentOrderDto;

import java.util.List;

public interface FulFillmentService {
    List<FulFillmentOrderDto> getFullFillmentOrders(Long order_id);

    FulFillmentDto createFulFillments(FulFillmentRequestModel fulFillmentRequest);
}
