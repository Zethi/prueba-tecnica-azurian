package com.github.zethi.pruebatecnicaazurian.repository;

import com.github.zethi.pruebatecnicaazurian.entity.City;
import com.github.zethi.pruebatecnicaazurian.entity.Province;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findByProvince(Province province);
}
