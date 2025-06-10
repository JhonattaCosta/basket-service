package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.PlatziProductResponse;
import dev.java.ecommerce.basketservice.client.PlatziStoreClient;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts(){
        log.info("Getting all Products");
        return platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "products", key = "#id")
    public PlatziProductResponse getProductId(Long id){
        log.info("Getting product with id: {}", id);
        return platziStoreClient.getProductId(id);
    }

}