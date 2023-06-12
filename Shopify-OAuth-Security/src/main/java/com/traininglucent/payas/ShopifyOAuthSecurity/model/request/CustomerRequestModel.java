package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestModel {

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private Boolean verified_email;
    private String password;
    private String password_confirmation;
    private List<AddressDto> addresses;

}
