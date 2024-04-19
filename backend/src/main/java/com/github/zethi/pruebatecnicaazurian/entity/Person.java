package com.github.zethi.pruebatecnicaazurian.entity;

import com.github.zethi.pruebatecnicaazurian.constant.CivilStatus;
import com.github.zethi.pruebatecnicaazurian.constant.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Field 'firstName' can not be empty")
    @Size(min = 1, max = 60, message = "The field 'First name' can only have a length between 1-60 characters")
    @Column(name = "first_name", unique = false, nullable = false)
    private String firstName;

    @NotBlank(message = "Field 'lastName' can not be empty")
    @Size(min = 1, max = 60, message = "The field 'Last name' can only have a length between 1-60 characters")
    @Column(name = "last_name", unique = false, nullable = false)
    private String lastName;

    @Pattern(
            regexp = "^(\\d{1,3}(?:\\.\\d{1,3}){2}-[\\dkK])$",
            flags = Pattern.Flag.UNICODE_CASE,
            message = "RUT entered is invalid, please enter one that complies with Chilean regulations."
    )
    @NotBlank(message = "Field 'rut' can not be empty")
    @Column(name = "RUT", unique = true, nullable = false)
    private String RUT;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "civil_status", nullable = false)
    private CivilStatus civilStatus;

    @OneToOne(targetEntity = Address.class, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRUT() {
        return RUT;
    }

    public void setRUT(String RUT) {
        this.RUT = RUT;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
