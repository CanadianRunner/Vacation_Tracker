package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "excursions")
@Getter
@Setter
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id", nullable = false, updatable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "excursion_title")
    @JsonProperty("excursion_title")
    private String excursionTitle;

    @Column(name = "excursion_price")
    @JsonProperty("excursion_price")
    private BigDecimal excursionPrice;

    @Column(name = "image_url")
    @JsonProperty("image_URL")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacation_id")
    @JsonProperty("vacation")
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions")
    @JsonProperty("cart_items")
    private Set<CartItem> cartItems;
}
