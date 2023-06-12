package com.traininglucent.payas.ShopifyOAuthSecurity.repository;

import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
