package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.PlatziProductResponse;
import dev.java.ecommerce.basketservice.client.PlatziStoreClient;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;


    public List<PlatziProductResponse> getAllProducts(){
        return platziStoreClient.getAllProducts();
    }
    public PlatziProductResponse getProductId(Long id){
        return platziStoreClient.getProductId(id);
    }

}