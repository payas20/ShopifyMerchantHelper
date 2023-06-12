package com.traininglucent.payas.ShopifyOAuthSecurity.repository;

import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, Long> {
}
