package com.traininglucent.payas.ShopifyOAuthSecurity.service.impl;


import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Product;
import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Products;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ProductEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ProductRepository;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ShopifyAdminAccessTokenRepo;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.ProductService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.OptionDto;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ShopifyAdminAccessTokenRepo accessTokenRepo;

    @Autowired
    ProductRepository productRepository;

    private final String shop = "training-store-lucent.myshopify.com";

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        Product product = new Product(productDto);
        System.out.println(product);

        WebClient webClient = WebClient.create();

        Product createdProduct = webClient.post()
                .uri("https://" + shop + "/admin/api/2023-04/products.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .body(Mono.just(product), Product.class)
                .retrieve()
                .bodyToMono(Product.class).block();
        System.out.println("Product Accepted");

        ProductDto returnValue = createdProduct.getProduct();

        ModelMapper modelMapper = new ModelMapper();
        ProductEntity storedProduct = modelMapper.map(returnValue, ProductEntity.class);

//        storedProduct.setId(returnValue.getId());
//        storedProduct.setTitle(returnValue.getTitle());
//        storedProduct.setProduct_type(returnValue.getProduct_type());
//        storedProduct.setStatus(returnValue.getStatus());
//        storedProduct.setVendor(returnValue.getVendor());

//        ModelMapper modelMapper = new ModelMapper();
//        for(int i=0;i<returnValue.getOptions().size();i++){
//            OptionDto optionDto = returnValue.getOptions().get(i);
//            OptionRepository optionEntity = modelMapper.map(optionDto, OptionRepository.class);
//            String s = optionEntity.getValues().toString();
//            optionEntity.setValues(optionDto.getValues().toString().substring(1, s.length()-2));
////            storedProduct.getOptions().add(optionEntity);
//        }
        System.out.println(storedProduct);

       ProductEntity ps = productRepository.save(storedProduct);
       log.info("ProductServiceImpl|ps = {}",ps);

        return returnValue;
    }

    @Override
    public ProductDto getProduct(Long id) {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        Product storedProduct = webClient.get()
                .uri("https://" + shop + "/admin/api/2023-04/products/" +id + ".json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(Product.class).block();

        return storedProduct.getProduct();
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {

        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product Not found"));

        productEntity.setTitle(productDto.getTitle());

        productRepository.save(productEntity);

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        ModelMapper modelMapper = new ModelMapper();
        productDto = modelMapper.map(productEntity, ProductDto.class);
        Product product = new Product(productDto);
        System.out.println(productDto.toString());

        WebClient webClient = WebClient.create();

        Product updatedProduct = webClient.put()
                .uri("https://" + shop + "/admin/api/2023-04/products/" + id + ".json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .body(Mono.just(product), Product.class)
                .retrieve()
                .bodyToMono(Product.class).block();

        ProductDto returnValue = updatedProduct.getProduct();

        return returnValue;
    }

    @Override
    public String deleteProduct(Long id) {

        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product Not found"));

        productRepository.delete(productEntity);

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        Product deletedProduct = webClient.delete()
                .uri("https://" + shop + "/admin/api/2023-04/products/" + id + ".json")
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(Product.class).block();

        return "Product Deleted Successfully";
    }

    @Override
    public List<ProductDto> getAllProducts() {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        Products storedProduct = webClient.get()
                .uri("https://" + shop + "/admin/api/2023-04/products.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(Products.class).block();

        return storedProduct.getProducts();
    }
}
