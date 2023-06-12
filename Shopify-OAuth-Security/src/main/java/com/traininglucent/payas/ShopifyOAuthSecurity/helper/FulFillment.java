package com.traininglucent.payas.ShopifyOAuthSecurity.helper;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.FulFillmentRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FulFillment {
    private FulFillmentRequestModel fulfillment;
}
