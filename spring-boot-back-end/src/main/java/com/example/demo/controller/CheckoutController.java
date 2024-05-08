package com.example.demo.controller;

import com.example.demo.services.CheckoutService;
import com.example.demo.services.PurchaseData;
import com.example.demo.services.PurchaseResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@Valid @RequestBody PurchaseData purchase) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }
}







