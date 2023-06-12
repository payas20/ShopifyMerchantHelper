package com.traininglucent.payas.ShopifyOAuthSecurity.model.response;

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
public class OrderRest {
    private Long id;
    private Long app_id;
    private String currency;
    private String financial_status;
    private Integer order_number;
    private String order_status_url;
    private Double total_price;
    private List<LineItemsRest> line_items;
    private AddressDto shipping_address;
}
