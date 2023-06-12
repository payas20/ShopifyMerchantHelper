package com.traininglucent.payas.ShopifyOAuthSecurity.repository;

import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopifyAdminAccessTokenRepo extends JpaRepository<ShopifyAdminAccessTokenEntity, Long> {
    ShopifyAdminAccessTokenEntity findByShop(String shop);
    ShopifyAdminAccessTokenEntity findByAccessToken(String accessToken);
}
