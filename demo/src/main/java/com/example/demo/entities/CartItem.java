package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    @JsonProperty("cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vacation_id")
    @JsonProperty("vacation")
    private Vacation vacation;

    @ManyToMany
    @JoinTable(
            name = "excursion_cartitem",
            joinColumns = @JoinColumn(name = "cart_item_id"),
            inverseJoinColumns = @JoinColumn(name = "excursion_id")
    )
    @JsonProperty("excursions")
    private Set<Excursion> excursions;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonProperty("cart")
    private Cart cart;

    @CreationTimestamp
    @Column(name = "create_date")
    @JsonProperty("create_date")
    private Date create_date;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date last_update;

}