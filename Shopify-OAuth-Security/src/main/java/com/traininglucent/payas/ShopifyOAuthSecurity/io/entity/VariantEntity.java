package com.traininglucent.payas.ShopifyOAuthSecurity.io.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariantEntity {
    @Id
    private Long id;
    private String title;
    private Double price;
    private String sku;
    private Integer position;
    private String option1;
    private String option2;
    private String option3;
}
