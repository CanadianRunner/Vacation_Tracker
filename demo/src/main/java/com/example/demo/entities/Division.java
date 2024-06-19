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
@Table(name="divisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Division{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "division_id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "division")
    @JsonProperty("division_name")
    private String division_name;

    @CreationTimestamp
    @Column(name = "create_date")
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonProperty("country_id")
    private Country country;

    @Column(name = "country_id", insertable = false, updatable = false)
    private Long countryID;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Customer> customers;

    public void setCountry(Country country) {
        this.country = country;
        this.countryID = (country != null) ? country.getId() : null;
    }
}