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
public class OrderRequestModel {
    private List<LineItemsRequestModel> line_items;
    private AddressDto shipping_address;
    private String email;
//    private List<TransactionsRequestModel> transactions;
    private String financial_status;

}
