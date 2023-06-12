package com.traininglucent.payas.ShopifyOAuthSecurity.controller;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.ProductRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.CustomerRest;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.ProductRest;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.ProductService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.CustomerDto;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/v1/view/products")
    public ResponseEntity<List<ProductRest>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();

        ModelMapper modelMapper = new ModelMapper();
        List<ProductRest> allProducts = new ArrayList<>();
        for(int i=0;i<products.size();i++){
            ProductDto productDto = products.get(i);
            ProductRest productRest = modelMapper.map(productDto, ProductRest.class);
            allProducts.add(productRest);
        }

        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/v1/create/product")
    public ResponseEntity<ProductRest> createProduct(@RequestBody ProductRequestModel productRequest){

//        ProductDto productDto = new ProductDto();
//        BeanUtils.copyProperties(productRequest, productDto);

        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);

        ProductDto createdProduct = productService.createProduct(productDto);

//        ProductRest returnValue = new ProductRest();
//        BeanUtils.copyProperties(createdProduct, returnValue);
        ProductRest returnValue = modelMapper.map(createdProduct, ProductRest.class);

        return ResponseEntity.ok()
                .body(returnValue);
    }

    @GetMapping("/v1/view/product/{id}")
    public ResponseEntity<ProductRest> getProduct(@PathVariable Long id){
        ProductDto productDto = productService.getProduct(id);

        ModelMapper modelMapper = new ModelMapper();
        ProductRest returnBody = modelMapper.map(productDto, ProductRest.class);
//        BeanUtils.copyProperties(productDto, returnBody);

        return ResponseEntity.ok()
                .body(returnBody);
    }

    @PutMapping("/v1/update/product/{id}")
    public ResponseEntity<ProductRest> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestModel productRequest){

        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
//        BeanUtils.copyProperties(productRequest, productDto);

        ProductDto updatedProduct = productService.updateProduct(id, productDto);


        ProductRest returnBody = modelMapper.map(updatedProduct, ProductRest.class);
//        BeanUtils.copyProperties(updatedProduct, returnValue);

        return ResponseEntity.ok()
                .body(returnBody);
    }

    @DeleteMapping("/v1/delete/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){

        String returnValue = productService.deleteProduct(id);

        return ResponseEntity.ok()
                .body(returnValue);
    }


}
