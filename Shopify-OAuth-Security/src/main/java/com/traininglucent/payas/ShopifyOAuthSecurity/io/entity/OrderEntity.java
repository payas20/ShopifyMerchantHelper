package com.traininglucent.payas.ShopifyOAuthSecurity.io.entity;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.LineItemsDto;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.TransactionsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<LineItemsEntity> line_items;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private ShippingAddressEntity shipping_address;

//    @OneToMany
//    @JoinColumn(name = "order_id")
//    private List<TransactionsEntity> transactions;

//    private Double tax;
//    private String currency;
    private Long app_id;
    private String financial_status;
    private Integer order_number;
    private Double total_price;

}
