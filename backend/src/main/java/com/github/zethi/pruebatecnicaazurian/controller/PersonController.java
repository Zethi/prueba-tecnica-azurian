package com.github.zethi.pruebatecnicaazurian.controller;

import com.github.zethi.pruebatecnicaazurian.entity.Address;
import com.github.zethi.pruebatecnicaazurian.entity.Person;
import com.github.zethi.pruebatecnicaazurian.exception.CityNotFoundException;
import com.github.zethi.pruebatecnicaazurian.exception.PersonNotFoundException;
import com.github.zethi.pruebatecnicaazurian.request.CreatePersonRequest;
import com.github.zethi.pruebatecnicaazurian.service.AddressService;
import com.github.zethi.pruebatecnicaazurian.service.CityService;
import com.github.zethi.pruebatecnicaazurian.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;
    private final AddressService addressService;
    private final CityService cityService;

    @Autowired
    public PersonController(PersonService personService, AddressService addressService, CityService cityService) {
        this.personService = personService;
        this.addressService = addressService;
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPersonById(@PathVariable Long id) throws PersonNotFoundException {
        final Person person = this.personService.getById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("person", person);

        return ResponseEntity.ok(body);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {
        final Person[] persons = this.personService.getAll();

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("persons", persons);

        return ResponseEntity.ok(body);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest,
                                                            UriComponentsBuilder uriComponentsBuilder
    ) throws CityNotFoundException {
        final Address address = addressService.create(createPersonRequest.address(), cityService.getById(createPersonRequest.address().city_id()));
        final Person person = personService.create(createPersonRequest, address);

        final URI locationOfNewPerson = uriComponentsBuilder
                .path("api/person/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        Map<String, Object> body = new HashMap<>();
        body.put("status", "created");

        return ResponseEntity.created(locationOfNewPerson).body(body);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String, Object>> updatePerson(@PathVariable long id, @Valid @PathVariable Person updatedPerson) throws PersonNotFoundException {
        final Person person = this.personService.getById(id);

        personService.update(person, updatedPerson);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "updated");

        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePersonById(@PathVariable long id) {
        this.personService.deleteById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "deleted");

        return ResponseEntity.ok(body);
    }
}
