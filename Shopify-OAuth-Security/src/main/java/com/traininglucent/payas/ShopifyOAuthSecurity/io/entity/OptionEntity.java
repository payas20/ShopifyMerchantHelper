package com.traininglucent.payas.ShopifyOAuthSecurity.io.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "options")
@Data
public class OptionEntity {
    @Id
    private Long id;
    private String name;
    private Integer position;
    @Column(name = "value")
    private List<String> values;
}
