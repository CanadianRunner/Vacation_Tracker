package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    @JsonProperty("cart_id")
    private Long id;

    @Column(name = "order_tracking_number", nullable = false)
    @JsonProperty("order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "party_size", nullable = false)
    @JsonProperty("party_size")
    private int partySize;

    @Column(name = "package_price", nullable = false)
    @JsonProperty("package_price")
    private BigDecimal packagePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    private StatusType status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonProperty("customer")
    private Customer customer;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    @JsonProperty("cart_items")
    private Set<CartItem> cartItems = new HashSet<>();

    public void addItem(CartItem item) {
        this.cartItems.add(item);
    }
}
