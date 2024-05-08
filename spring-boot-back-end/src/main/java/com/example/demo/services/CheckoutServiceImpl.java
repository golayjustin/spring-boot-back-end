package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Customer;
import com.example.demo.entity.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CartRepository cartRepository;

    public CheckoutServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(PurchaseData purchase) {

        // Check if the cart is empty
        Set<CartItem> cartItems = purchase.getCartItems();
        if (cartItems == null || cartItems.isEmpty()) {
            return new PurchaseResponse("Cart Empty");
        }

        // retrieve order info
        Cart cart = purchase.getCart();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // set order status
        cart.setStatus(StatusType.ordered);

        // populate cart with cartItems
        cartItems.forEach(item -> cart.add(item));

        // populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // save to database
        cartRepository.save(cart);

        // return a response
            return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number
        return UUID.randomUUID().toString();
    }
}
