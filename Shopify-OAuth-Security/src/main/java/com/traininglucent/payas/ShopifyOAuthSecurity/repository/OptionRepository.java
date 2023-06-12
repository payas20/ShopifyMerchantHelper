package com.traininglucent.payas.ShopifyOAuthSecurity.repository;

import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
}
