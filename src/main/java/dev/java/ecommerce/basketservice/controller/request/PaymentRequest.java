package dev.java.ecommerce.basketservice.controller.request;

import dev.java.ecommerce.basketservice.entity.PaymentMetod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private PaymentMetod paymentMetod;
}
