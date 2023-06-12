package com.traininglucent.payas.ShopifyOAuthSecurity.service.impl;

import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.ShopLoginRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.ShopLoginRest;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ShopifyAdminAccessTokenRepo;
import com.traininglucent.payas.ShopifyOAuthSecurity.security.JwtService;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.ShopifyAccessTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopifyAccessTokenServiceImpl implements ShopifyAccessTokenService {

    @Autowired
    ShopifyAdminAccessTokenRepo accessTokenRepo;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public ShopLoginRest merchantLogin(ShopLoginRequestModel request) {

        ShopifyAdminAccessTokenEntity merchant = accessTokenRepo.findByShop(request.getShop());

        if(merchant == null)
            throw new RuntimeException("Record doesn't exists");

        request.setAccessToken(merchant.getAccessToken());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getShop(), request.getAccessToken()
                )
        );

        ShopLoginRest returnValue = new ShopLoginRest();
        var jwtToken = jwtService.generateToken(merchant);
        returnValue.setJwtToken(jwtToken);

        return returnValue;
    }
}
