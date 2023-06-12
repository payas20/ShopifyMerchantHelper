package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.LineItemsRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.TransactionsRequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private List<LineItemsDto> line_items;
    private AddressDto shipping_address;
//    private List<TransactionsDto> transactions;
    private String email;
    private Double tax;
    private String currency;
    private Long app_id;
    private String financial_status;
    private Integer order_number;
    private String order_status_url;
    private Double total_price;

}
