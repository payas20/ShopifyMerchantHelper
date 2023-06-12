package com.traininglucent.payas.ShopifyOAuthSecurity.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemsRequestModel {
//    private String title;
//    private Double price;
//    private String  grams;
    private Long variant_id;
    private Integer quantity;
//    private List<TaxLinesRequestModel> tax_lines;
}
