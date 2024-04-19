package com.github.zethi.pruebatecnicaazurian.repository;

import com.github.zethi.pruebatecnicaazurian.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
