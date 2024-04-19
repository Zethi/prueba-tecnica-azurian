package com.github.zethi.pruebatecnicaazurian.controller;

import com.github.zethi.pruebatecnicaazurian.entity.City;
import com.github.zethi.pruebatecnicaazurian.entity.Province;
import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.CityNotFoundException;
import com.github.zethi.pruebatecnicaazurian.exception.ProvinceNotFoundException;
import com.github.zethi.pruebatecnicaazurian.exception.RegionNotFoundException;
import com.github.zethi.pruebatecnicaazurian.request.CreateCityRequest;
import com.github.zethi.pruebatecnicaazurian.service.CityService;
import com.github.zethi.pruebatecnicaazurian.service.ProvinceService;
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
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;
    private final ProvinceService provinceService;

    @Autowired

    public CityController(CityService cityService, ProvinceService provinceService) {
        this.cityService = cityService;
        this.provinceService = provinceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCityById(@PathVariable Long id) throws CityNotFoundException {
        final City city = this.cityService.getById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("city", city);

        return ResponseEntity.ok(body);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllCities() {
        final City[] cities = this.cityService.getAll();

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("cities", cities);

        return ResponseEntity.ok(body);
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<Map<String, Object>> getAllProvincesByRegionId(@PathVariable long id) throws ProvinceNotFoundException {
        final Province province = this.provinceService.getById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("provinces", this.cityService.getAllByProvince(province));

        return ResponseEntity.ok(body);
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> createCity(@Valid @RequestBody CreateCityRequest createCityRequest, UriComponentsBuilder uriComponentsBuilder) throws ProvinceNotFoundException {
        final City city = this.cityService.create(createCityRequest, this.provinceService.getById(createCityRequest.province_id()));

        final URI locationOfNewCity = uriComponentsBuilder
                .path("api/city/{id}")
                .buildAndExpand(city.getId())
                .toUri();

        Map<String, Object> body = new HashMap<>();
        body.put("status", "created");

        return ResponseEntity.created(locationOfNewCity).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCityById(@PathVariable long id) {
        this.cityService.deleteById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "deleted");

        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCityById(@PathVariable long id, @Valid @RequestBody City updatedCity) throws CityNotFoundException {
        final City city = this.cityService.getById(id);

        cityService.update(city, updatedCity);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "updated");

        return ResponseEntity.ok(body);
    }
}
