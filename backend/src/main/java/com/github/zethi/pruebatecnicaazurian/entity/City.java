package com.github.zethi.pruebatecnicaazurian.entity;

import jakarta.persistence.*;

@Entity()
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne(targetEntity = Province.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
