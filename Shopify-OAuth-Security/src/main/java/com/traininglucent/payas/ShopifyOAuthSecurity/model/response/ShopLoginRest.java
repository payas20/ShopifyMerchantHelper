package com.traininglucent.payas.ShopifyOAuthSecurity.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopLoginRest {
    private String jwtToken;
}
