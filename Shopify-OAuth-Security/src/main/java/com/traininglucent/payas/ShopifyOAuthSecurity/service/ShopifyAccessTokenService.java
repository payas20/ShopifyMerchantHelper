package com.traininglucent.payas.ShopifyOAuthSecurity.service;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.ShopLoginRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.ShopLoginRest;

public interface ShopifyAccessTokenService {
    ShopLoginRest merchantLogin(ShopLoginRequestModel request);
}
