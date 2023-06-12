package com.traininglucent.payas.ShopifyOAuthSecurity.repository;

import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);
}
