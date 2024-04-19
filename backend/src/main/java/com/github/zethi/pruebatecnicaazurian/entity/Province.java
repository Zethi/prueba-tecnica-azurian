package com.github.zethi.pruebatecnicaazurian.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Field 'name' can not be empty")
    @Size(min = 1, max = 30, message = "The field 'First name' can only have a length between 1-30 characters")
    @Column(name = "name", unique = false, nullable = false)
    private String name;


    @ManyToOne(targetEntity = Region.class, optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "region_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field 'region_id' can not be empty")
    private Region region;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
