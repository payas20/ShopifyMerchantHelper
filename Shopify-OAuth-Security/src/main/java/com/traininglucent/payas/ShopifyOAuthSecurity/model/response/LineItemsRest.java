package com.traininglucent.payas.ShopifyOAuthSecurity.model.response;

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
public class LineItemsRest {
    private Long id;
    private Integer fulfillable_quantity;
    private String title;
    private Double price;
    private String  grams;
    private Integer quantity;
    private Long product_id;
    private Long variant_id;
}
