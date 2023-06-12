package com.traininglucent.payas.ShopifyOAuthSecurity.helper;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customers {
    private List<CustomerDto> customers;
}
