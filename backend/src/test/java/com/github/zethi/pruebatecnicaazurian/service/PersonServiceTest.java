package com.github.zethi.pruebatecnicaazurian.service;


import com.github.zethi.pruebatecnicaazurian.constant.CivilStatus;
import com.github.zethi.pruebatecnicaazurian.constant.Gender;
import com.github.zethi.pruebatecnicaazurian.entity.*;
import com.github.zethi.pruebatecnicaazurian.exception.PersonNotFoundException;
import com.github.zethi.pruebatecnicaazurian.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceTest {

    private final PersonService personService;
    private Person samplePerson = new Person();
    private Address sampleAddress = new Address();
    private City sampleCity = new City();
    private Province sampleProvince = new Province();
    private Region sampleRegion = new Region();

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    public PersonServiceTest(PersonService personService) {
        this.personService = personService;



        this.sampleRegion.setId(1L);
        this.sampleRegion.setName("Test Region");
        this.sampleRegion.setOrdinal("I");

        this.sampleProvince.setId(1L);
        this.sampleProvince.setName("Test Province");
        this.sampleProvince.setRegion(sampleRegion);

        this.sampleCity.setId(1L);
        this.sampleCity.setProvince(sampleProvince);
        this.sampleCity.setName("Test City");

        this.sampleAddress.setId(1L);
        this.sampleAddress.setStreetName("Example Street Name");
        this.sampleAddress.setNumber(1000);

        this.samplePerson.setId(1L);
        this.samplePerson.setRUT("12.345.678-9");
        this.samplePerson.setFirstName("John");
        this.samplePerson.setLastName("Doe");
        this.samplePerson.setGender(Gender.MALE);
        this.samplePerson.setCivilStatus(CivilStatus.UNMARRIED);
        this.samplePerson.setAddress(sampleAddress);

    }

    @Test
    public void testExistantCaseById() throws PersonNotFoundException {

        when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(this.samplePerson));

        final Person foundPerson = personService.getById(1L);

        assertThat(foundPerson).isNotNull();
        assertThat(foundPerson.getId()).isEqualTo(1L);
        assertThat(foundPerson.getRUT()).isEqualTo("12.345.678-9");
        assertThat(foundPerson.getFirstName()).isEqualTo("John");
        assertThat(foundPerson.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testNotExistentCaseById() {
        assertThatThrownBy(() -> personService.getById(100L)).isInstanceOf(PersonNotFoundException.class);
    }

    @Test
    public void testGetAllCase() {
        when(personRepository.findAll()).thenReturn(List.of(new Person[]{samplePerson, samplePerson, samplePerson}));

        final Person[] allPersons = personService.getAll();

        assertThat(allPersons).hasSize(3);
        assertThat(allPersons[0].getRUT()).isEqualTo("12.345.678-9");
        assertThat(allPersons[1].getRUT()).isEqualTo("12.345.678-9");
        assertThat(allPersons[2].getRUT()).isEqualTo("12.345.678-9");
        assertThat(allPersons[0].getFirstName()).isEqualTo("John");
        assertThat(allPersons[1].getFirstName()).isEqualTo("John");
        assertThat(allPersons[2].getFirstName()).isEqualTo("John");



    }

    @Test
    public void updateCase() throws PersonNotFoundException {
        Person updatedPerson = new Person();
        updatedPerson.setId(1L);
        updatedPerson.setRUT("12.345.678-k");
        updatedPerson.setFirstName("John Updated");
        updatedPerson.setLastName("Doe Updated");
        updatedPerson.setGender(Gender.MALE);
        updatedPerson.setCivilStatus(CivilStatus.UNMARRIED);
        updatedPerson.setAddress(sampleAddress);

        this.personService.update(samplePerson, updatedPerson);

        when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(this.samplePerson));

        final Person foundPerson = personService.getById(1L);

        assertThat(foundPerson.getRUT()).isEqualTo("12.345.678-k");
        assertThat(foundPerson.getFirstName()).isEqualTo("John Updated");
        assertThat(foundPerson.getLastName()).isEqualTo("Doe Updated");

    }

    @Test
    public void deleteById() throws PersonNotFoundException {
        when(personRepository.findById(1L)).thenReturn(Optional.ofNullable(this.samplePerson));
        final Person foundPerson = personService.getById(1L);
        assertThat(foundPerson).isNotNull();


        this.personService.deleteById(1L);
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> personService.getById(1L)).isInstanceOf(PersonNotFoundException.class);
    }
}
