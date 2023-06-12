package com.traininglucent.payas.ShopifyOAuthSecurity.service;

import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(Long id);

    ProductDto updateProduct(Long id, ProductDto productDto);

    String deleteProduct(Long id);

    List<ProductDto> getAllProducts();
}
