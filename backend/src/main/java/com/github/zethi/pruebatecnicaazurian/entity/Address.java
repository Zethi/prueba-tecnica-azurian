package com.github.zethi.pruebatecnicaazurian.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Field 'Street name' can not be empty")
    @Size(min = 1, max = 1000)
    @Column(name = "street_name", unique = false, nullable = false)
    private String streetName;

    @NotNull(message = "Field 'number' can not be empty")
    @Column(name = "number", unique = false, nullable = false)
    private int number;

    @ManyToOne(targetEntity = City.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private City city;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public Province getProvince() {
        return city.getProvince();
    }

    public Region getRegion() {
        return city.getProvince().getRegion();
    }

    public String getStreetName() {
        return streetName;
    }

    public int getNumber() {
        return number;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
