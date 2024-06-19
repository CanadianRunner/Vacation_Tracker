package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    @JsonProperty("customer_id")
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Column(name = "customer_first_name", nullable = false)
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Column(name = "customer_last_name", nullable = false)
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address must not exceed 255 characters")
    @Column(name = "address", nullable = false)
    @JsonProperty("address")
    private String address;

    @NotBlank(message = "Postal code is required")
    @Size(min = 6, max = 7, message = "Postal code must be between 6 and 7 characters")
    @Column(name = "postal_code", nullable = false)
    @JsonProperty("postal_code")
    private String postalCode;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Please enter a valid ten digit phone number")
    @Column(name = "phone", nullable = false)
    @JsonProperty("phone")
    private String phone;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    @JsonProperty("division")
    private Division division;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonProperty("carts")
    private Set<Cart> carts = new HashSet<>();

    public void addCart(Cart cart) {
        this.carts.add(cart);
        //cart.setCustomer(this);
    }
}
