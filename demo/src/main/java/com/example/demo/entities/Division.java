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
@Table(name = "divisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    @JsonProperty("division_id")
    private Long id;

    @Column(name = "division")
    @JsonProperty("division")
    private String divisionName;

    @Column(name = "country_id", insertable = false, updatable = false)
    @JsonProperty("country_id")
    private Long countryID;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    @JsonProperty("create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    @JsonProperty("last_update")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @JsonProperty("country")
    private Country country;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("customers")
    private Set<Customer> customers;

    public void setCountry(Country country) {
        this.country = country;
        this.countryID = (country != null) ? country.getId() : null;
    }
}
