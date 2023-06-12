package com.traininglucent.payas.ShopifyOAuthSecurity.model.response;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.AddressDto;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRest {
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private List<AddressDto> addresses;
}
