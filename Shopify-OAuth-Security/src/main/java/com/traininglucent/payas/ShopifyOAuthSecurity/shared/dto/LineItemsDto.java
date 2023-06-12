package com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.TaxLinesRequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemsDto {
//    private String title;
//    private Double price;
//    private String  grams;
    private Integer quantity;
//    private List<TaxLinesDto> tax_lines;
    private Long id;
    private Integer fulfillable_quantity;
    private Long product_id;
    private Long variant_id;
}
