package com.traininglucent.payas.ShopifyOAuthSecurity.service.impl;

import com.traininglucent.payas.ShopifyOAuthSecurity.exceptions.CustomerServiceException;
import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Customer;
import com.traininglucent.payas.ShopifyOAuthSecurity.helper.Customers;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.CustomerEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.io.entity.ShopifyAdminAccessTokenEntity;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.ErrorMessages;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.CustomerRepository;
import com.traininglucent.payas.ShopifyOAuthSecurity.repository.ShopifyAdminAccessTokenRepo;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.CustomerService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    ShopifyAdminAccessTokenRepo accessTokenRepo;

    @Autowired
    CustomerRepository customerRepository;

    private final String shop = "training-store-lucent.myshopify.com";

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        CustomerEntity customerEntity = customerRepository.findByEmail(customerDto.getEmail());
        if(customerEntity!=null) throw new CustomerServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        Customer customer = new Customer(customerDto);

        WebClient webClient = WebClient.create();

        Customer createdCustomer = webClient.post()
                .uri("https://" + shop + "/admin/api/2023-04/customers.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToMono(Customer.class).block();

        CustomerDto returnValue = createdCustomer.getCustomer();

        ModelMapper modelMapper = new ModelMapper();
        CustomerEntity myCustomer = modelMapper.map(returnValue, CustomerEntity.class);

        customerRepository.save(myCustomer);

        return returnValue;
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {


        Optional<CustomerEntity> myCustomer = customerRepository.findById(id);
        if(myCustomer.isEmpty()) throw new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        CustomerEntity customerEntity = myCustomer.get();

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        Customer customer = new Customer(customerDto);

        WebClient webClient = WebClient.create();

        Customer updatedCustomer = webClient.put()
                .uri("https://" + shop + "/admin/api/2023-04/customers/" + id + ".json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToMono(Customer.class).block();

        CustomerDto returnValue = updatedCustomer.getCustomer();

        customerEntity.setFirst_name(returnValue.getFirst_name());
        customerEntity.setLast_name(returnValue.getLast_name());
        customerEntity.setEmail(returnValue.getEmail());
        customerEntity.setPhone(returnValue.getPhone());
        customerEntity.setVerified_email(returnValue.isVerified_email());

        customerRepository.save(customerEntity);

        return returnValue;
    }

    @Override
    public String deleteCustomer(Long id) {

        String returnValue = new String();

        CustomerEntity customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        customerRepository.delete(customerEntity);

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();


        Customer customer = webClient.delete()
                .uri("https://" + shop + "/admin/api/2023-04/customers/" + id + ".json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(Customer.class).block();


        return "Customer Deleted Successfully";
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        Customers customers = webClient.get()
                .uri("https://" + shop + "/admin/api/2023-04/customers.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(Customers.class).block();

        return customers.getCustomers();
    }

    @Override
    public Object getCustomer(Long id) {
        ShopifyAdminAccessTokenEntity accessTokenEntity = accessTokenRepo.findByShop(shop);

        WebClient webClient = WebClient.create();

        Object customer = webClient.get()
                .uri("https://" + shop + "/admin/api/2023-04/customers/" + id +".json")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-Shopify-Access-Token", accessTokenEntity.getAccessToken())
                .retrieve()
                .bodyToMono(Object.class).block();

        return customer;
    }
}
