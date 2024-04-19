package com.github.zethi.pruebatecnicaazurian.service;

import com.github.zethi.pruebatecnicaazurian.entity.City;
import com.github.zethi.pruebatecnicaazurian.entity.Province;
import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.CityNotFoundException;
import com.github.zethi.pruebatecnicaazurian.repository.CityRepository;
import com.github.zethi.pruebatecnicaazurian.request.CreateCityRequest;
import com.github.zethi.pruebatecnicaazurian.request.CreateProvinceRequest;
import com.github.zethi.pruebatecnicaazurian.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City getById(long id) throws CityNotFoundException {
        return this.cityRepository.findById(id).orElseThrow(CityNotFoundException::new);
    }

    public City[] getAll() {
        return IterableUtils.iteratorToArray(cityRepository.findAll().spliterator(), City[]::new);
    }

    public List<City> getAllByProvince(Province province) {
        return this.cityRepository.findByProvince(province);
    }

    public City create(CreateCityRequest createCityRequest, Province province) {
        City city = new City();

        city.setName(createCityRequest.name());
        city.setProvince(province);

        this.cityRepository.save(city);

        return city;
    }


    public void deleteById(long id) {
        this.cityRepository.deleteById(id);
    }
    public void update(City originalCity, City updatedCity) {
        originalCity.setName(updatedCity.getName());
        originalCity.setProvince(updatedCity.getProvince());

        this.cityRepository.save(originalCity);
    }
}
