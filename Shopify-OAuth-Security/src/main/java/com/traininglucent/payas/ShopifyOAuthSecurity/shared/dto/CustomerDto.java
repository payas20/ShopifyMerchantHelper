package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.CustomerRequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private boolean verified_email;
    private String password;
    private String password_confirmation;
    private List<AddressDto> addresses;
}
