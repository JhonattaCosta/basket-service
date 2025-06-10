package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.PlatziProductResponse;
import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.entity.Product;
import dev.java.ecommerce.basketservice.entity.Status;
import dev.java.ecommerce.basketservice.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Basket getBasketById(String id){
        return basketRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Basket not Found"));
    }


    public Basket createBasket(BasketRequest basketRequest){
        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    try {
                        throw new IllegalAccessException("There is already an open basket for this client");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });


        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest ->{
            PlatziProductResponse platziProductResponse = productService.getProductId(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });


        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(products)
                .build();
        basket.calculateTotalPrice();
        return basketRepository.save(basket);
    }

    public Basket updateBasket(String id, BasketRequest request){
        Basket savedBasket = getBasketById(id);
        List<Product> products = new ArrayList<>();
        request.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductId(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });
        savedBasket.setProducts(products);
        savedBasket.calculateTotalPrice();
        return basketRepository.save(savedBasket);

    }

    public Basket payBasket (String id, PaymentRequest request){
        Basket savedBasket = getBasketById(id);
        savedBasket.setPaymentMetod((request.getPaymentMetod()));
        savedBasket.setStatus(Status.SOLD);
        return basketRepository.save(savedBasket);

    }

    public void deleteBasket(String id){
        basketRepository.delete(getBasketById(id));
    }

}
