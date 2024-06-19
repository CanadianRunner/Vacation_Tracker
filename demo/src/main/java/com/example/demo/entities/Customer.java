package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    @JsonProperty("customer_id")
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @NonNull
    @Column(name = "customer_first_name")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "customer_last_name")
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    @JsonProperty("address")
    private String address;

    @NotBlank(message = "Postal code is mandatory")
    @Size(min = 5, max = 10, message = "Postal code must be between 5 and 10 characters")
    @Column(name = "postal_code")
    @JsonProperty("postal_code")
    private String postal_code;

    @NotBlank(message = "Phone number is mandatory")
    @Column(name = "phone")
    @JsonProperty("phone")
    private String phone;

    @CreationTimestamp
    @Column(name = "create_date")
    @JsonProperty("create_date")
    private Date create_date;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "division_id")
    @JsonProperty("division_id")
    private Division division;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cart> carts;
}
