package com.traininglucent.payas.ShopifyOAuthSecurity.controller;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.request.FulFillmentRequestModel;
import com.traininglucent.payas.ShopifyOAuthSecurity.service.FulFillmentService;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentDto;
import com.traininglucent.payas.ShopifyOAuthSecurity.shared.dto.FulFillmentOrderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FulFillmentController {

    @Autowired
    FulFillmentService fulFillmentService;

    @GetMapping("/v1/view/fulfillment_orders/{order_id}")
    public ResponseEntity<List> getFulFillmentOrders(@PathVariable Long order_id){

        List<FulFillmentOrderDto> fullFillmentOrderDto = fulFillmentService.getFullFillmentOrders(order_id);

        return ResponseEntity.ok()
                .body(fullFillmentOrderDto);
    }

    @PostMapping("/v1/create/fulfillments")
    public ResponseEntity<FulFillmentDto> createFulFillment(@RequestBody FulFillmentRequestModel fulFillmentRequest){

//        ModelMapper modelMapper = new ModelMapper();
//        FulFillmentDto fulFillmentDto = modelMapper.map(fulFillmentRequest, FulFillmentDto.class);

        FulFillmentDto returnValue = fulFillmentService.createFulFillments(fulFillmentRequest);

        return ResponseEntity.ok()
                .body(returnValue);
    }
}
