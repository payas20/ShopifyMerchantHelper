package com.traininglucent.payas.ShopifyOAuthSecurity.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessageRest {
    private Date timestamp;
    private String message;
}

