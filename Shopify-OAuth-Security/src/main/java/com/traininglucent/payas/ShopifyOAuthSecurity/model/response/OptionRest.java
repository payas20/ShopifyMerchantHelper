package com.traininglucent.payas.ShopifyOAuthSecurity.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionRest {
    private Long id;
    private Long product_id;
    private String name;
    private Integer position;
    private List<String> values;
}
