package com.traininglucent.payas.ShopifyOAuthSecurity.helper;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private OrderDto order;
}
