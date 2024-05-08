package com.example.demo.services;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Customer;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseData {
    private Customer customer;

    private Cart cart;

    private Set<CartItem> cartItems;
}
