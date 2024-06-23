package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        try {
            Cart cart = purchase.getCart();
            Customer customer = purchase.getCustomer();
            Set<CartItem> cartItems = purchase.getCartItems();
            String orderTrackingNumber = generateOrderTrackingNumber();

            if (customer == null) {
                throw new IllegalArgumentException("Customer information is required to proceed.");
            }

            if (cartItems == null || cartItems.isEmpty()) {
                throw new IllegalArgumentException("At least one cart item is required to proceed.");
            }

            for (CartItem item : cartItems) {
                item.setCart(cart);
            }

            cart.setOrderTrackingNumber(orderTrackingNumber);
            cart.setStatus(StatusType.ordered);
            cart.setCartItems(cartItems);

            cartRepository.save(cart);
            customerRepository.save(customer);

            return new PurchaseResponse(orderTrackingNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("An error occurred while placing the order: " + e.getMessage());
            throw new RuntimeException("An error occurred while placing the order", e);
        }
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
