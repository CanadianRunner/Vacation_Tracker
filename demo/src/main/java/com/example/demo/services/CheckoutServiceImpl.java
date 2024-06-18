package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        try {
            Customer customer = purchase.getCustomer();
            Cart cart = purchase.getCart();

            if (customer == null) {
                throw new IllegalArgumentException("Customer information is required.");
            }

            Set<CartItem> cartItems = purchase.getCartItems();
            if (cartItems == null || cartItems.isEmpty()) {
                throw new IllegalArgumentException("At least one cart item is required.");
            }

            String orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);

            for (CartItem cartItem : cartItems) {
                cart.addItem(cartItem);
            }

            cart.setCustomer(customer);
            cart.setStatus(StatusType.ordered);

            customerRepository.save(customer);
            cartRepository.save(cart);

            return new PurchaseResponse(orderTrackingNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
            throw e;
        }
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
