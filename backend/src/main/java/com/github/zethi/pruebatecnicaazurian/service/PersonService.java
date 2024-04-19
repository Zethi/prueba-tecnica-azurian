package com.github.zethi.pruebatecnicaazurian.service;

import com.github.zethi.pruebatecnicaazurian.constant.CivilStatus;
import com.github.zethi.pruebatecnicaazurian.constant.Gender;
import com.github.zethi.pruebatecnicaazurian.entity.Address;
import com.github.zethi.pruebatecnicaazurian.entity.Person;
import com.github.zethi.pruebatecnicaazurian.entity.Province;
import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.PersonNotFoundException;
import com.github.zethi.pruebatecnicaazurian.repository.PersonRepository;
import com.github.zethi.pruebatecnicaazurian.request.CreatePersonRequest;
import com.github.zethi.pruebatecnicaazurian.request.CreateProvinceRequest;
import com.github.zethi.pruebatecnicaazurian.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getById(long id) throws PersonNotFoundException {
        return this.personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public Person[] getAll() {
        return IterableUtils.iteratorToArray(this.personRepository.findAll().spliterator(), Person[]::new);
    }

    public Person create(CreatePersonRequest createPersonRequest, Address address) {
        Person person = new Person();

        person.setFirstName(createPersonRequest.firstName());
        person.setLastName(createPersonRequest.lastName());
        person.setRUT(createPersonRequest.rut());
        person.setGender(createPersonRequest.gender());
        person.setCivilStatus(createPersonRequest.civilStatus());
        person.setAddress(address);

        this.personRepository.save(person);

        return person;
    }

    public void update(Person originalPerson, Person updatedPerson) {
        originalPerson.setAddress(updatedPerson.getAddress());
        originalPerson.setCivilStatus(updatedPerson.getCivilStatus());
        originalPerson.setGender(updatedPerson.getGender());
        originalPerson.setFirstName(updatedPerson.getFirstName());
        originalPerson.setLastName(updatedPerson.getLastName());
        originalPerson.setRUT(updatedPerson.getRUT());

        this.personRepository.save(originalPerson);
    }
    public void deleteById(long id) {
        this.personRepository.deleteById(id);
    }
}
