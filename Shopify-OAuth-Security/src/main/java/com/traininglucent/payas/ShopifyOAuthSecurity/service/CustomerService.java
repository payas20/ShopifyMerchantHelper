package com.traininglucent.payas.ShopifyOAuthSecurity.service;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Long id, CustomerDto customerDto);

    String deleteCustomer(Long id);

    List<CustomerDto> getAllCustomers();

    Object getCustomer(Long id);
}
