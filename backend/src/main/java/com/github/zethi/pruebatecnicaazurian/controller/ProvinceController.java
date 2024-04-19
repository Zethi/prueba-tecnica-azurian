package com.github.zethi.pruebatecnicaazurian.controller;


import com.github.zethi.pruebatecnicaazurian.entity.Province;
import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.ProvinceNotFoundException;
import com.github.zethi.pruebatecnicaazurian.exception.RegionNotFoundException;
import com.github.zethi.pruebatecnicaazurian.request.CreateProvinceRequest;
import com.github.zethi.pruebatecnicaazurian.service.ProvinceService;
import com.github.zethi.pruebatecnicaazurian.service.RegionService;
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
@RequestMapping("/api/province")
public class ProvinceController {

    private final ProvinceService provinceService;
    private final RegionService regionService;

    @Autowired
    public ProvinceController(ProvinceService provinceService, RegionService regionService) {
        this.provinceService = provinceService;
        this.regionService = regionService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProvinceById(@PathVariable Long id) throws ProvinceNotFoundException {
        final Province province = this.provinceService.getById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("province", province);

        return ResponseEntity.ok(body);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllProvinces() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("provinces", this.provinceService.getAll());

        return ResponseEntity.ok(body);
    }

    @GetMapping("/region/{id}")
    public ResponseEntity<Map<String, Object>> getAllProvincesByRegionId(@PathVariable long id) throws RegionNotFoundException {
        final Region region = regionService.getById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("provinces", this.provinceService.getAllByRegion(region));

        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProvince(@Valid @RequestBody CreateProvinceRequest createProvinceRequest, UriComponentsBuilder uriComponentsBuilder) throws RegionNotFoundException {
        final Province province = this.provinceService.create(createProvinceRequest, regionService.getById(createProvinceRequest.region_id()));

        final URI locationOfNewProvince = uriComponentsBuilder
                .path("api/province/{id}")
                .buildAndExpand(province.getId())
                .toUri();

        Map<String, Object> body = new HashMap<>();
        body.put("status", "created");


        return ResponseEntity.created(locationOfNewProvince).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProvinceById(@PathVariable long id) {
        this.provinceService.deleteById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "deleted");

        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProvinceById(@PathVariable long id, @Valid @RequestBody Province updatedProvince) throws ProvinceNotFoundException {
        final Province province = this.provinceService.getById(id);

        provinceService.update(province, updatedProvince);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "updated");

        return ResponseEntity.ok(body);
    }
}
