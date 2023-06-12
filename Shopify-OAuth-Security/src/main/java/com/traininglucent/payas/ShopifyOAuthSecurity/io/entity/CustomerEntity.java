package com.traininglucent.payas.ShopifyOAuthSecurity.io.entity;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.AddressDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private boolean verified_email;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name ="customer_id")
    private List<AddressEntity> addresses;
}
