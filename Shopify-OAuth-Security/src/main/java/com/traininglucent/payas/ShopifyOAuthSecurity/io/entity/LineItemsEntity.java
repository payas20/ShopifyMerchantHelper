package com.traininglucent.payas.ShopifyOAuthSecurity.io.entity;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.TaxLinesDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "line_items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemsEntity {

    @Id
    private Long id;
    private Integer quantity;
    private Integer fulfillable_quantity;
    private Long product_id;
    private Long variant_id;
}
