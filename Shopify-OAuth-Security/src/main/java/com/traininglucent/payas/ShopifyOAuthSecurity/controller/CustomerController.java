package com.traininglucent.payas.ShopifyOAuthSecurity.controller;


import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.CustomerRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.CustomerRest;

import com.traininglucent.payas.ShopifyOAuthSecurity.service.CustomerService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.CustomerDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/v1/view/customers")
    public ResponseEntity<List<CustomerRest>> getAllCustomers(){
        List<CustomerDto> customers = customerService.getAllCustomers();

        ModelMapper modelMapper = new ModelMapper();
        List<CustomerRest> allCustomers = new ArrayList<>();
        for(int i=0;i<customers.size();i++){
            CustomerDto customerDto = customers.get(i);
            CustomerRest customerRest = modelMapper.map(customerDto, CustomerRest.class);
            allCustomers.add(customerRest);
        }

        return ResponseEntity.ok()
                .body(allCustomers);
    }

    @GetMapping("/v1/view/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        Object customer = customerService.getCustomer(id);

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/v1/create/customer")
    public ResponseEntity<CustomerRest> createCustomer(@RequestBody CustomerRequestModel customerRequest){

        ModelMapper modelMapper = new ModelMapper();
        CustomerDto customerDto = modelMapper.map(customerRequest, CustomerDto.class);


        CustomerDto createdCustomer = customerService.createCustomer(customerDto);

        CustomerRest returnBody = modelMapper.map(createdCustomer, CustomerRest.class);

        return ResponseEntity.ok()
                .body(returnBody);

    }

    @PutMapping("/v1/update/customer/{id}")
    public ResponseEntity<CustomerRest> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequestModel customerRequest){

        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customerRequest, customerDto);

        CustomerDto createdCustomer = customerService.updateCustomer(id,customerDto);
        CustomerRest returnBody = new CustomerRest();
        BeanUtils.copyProperties(createdCustomer, returnBody);


        return ResponseEntity.ok()
                .body(returnBody);
    }

    @DeleteMapping("/v1/delete/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        String returnValue = customerService.deleteCustomer(id);
        return ResponseEntity.ok()
                .body(returnValue);
    }

}
