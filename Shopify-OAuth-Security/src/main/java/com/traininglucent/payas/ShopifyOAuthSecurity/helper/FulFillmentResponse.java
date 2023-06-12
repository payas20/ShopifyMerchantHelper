package com.traininglucent.payas.ShopifyOAuthSecurity.helper;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FulFillmentResponse {
    private FulFillmentDto fulfillment;
}
