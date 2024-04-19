package com.github.zethi.pruebatecnicaazurian.controller;

import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.RegionNotFoundException;
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
@RequestMapping("/api/region")
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getRegionById(@PathVariable Long id) throws RegionNotFoundException {
        final Region region = this.regionService.getById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("region", region);

        return ResponseEntity.ok(body);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllRegions() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", "ok");
        body.put("regions", this.regionService.getAll());

        return ResponseEntity.ok(body);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createRegion(@Valid @RequestBody Region region, UriComponentsBuilder uriComponentsBuilder) {
        this.regionService.create(region);

        final URI locationOfNewRegion = uriComponentsBuilder
                .path("api/region/{id}")
                .buildAndExpand(region.getId())
                .toUri();

        Map<String, Object> body = new HashMap<>();
        body.put("status", "created");

        return ResponseEntity.created(locationOfNewRegion).body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteRegionById(@PathVariable long id) {
        this.regionService.deleteById(id);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "deleted");

        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateRegionById(@PathVariable long id, @Valid @RequestBody Region updatedRegion) throws RegionNotFoundException {
        final Region region = this.regionService.getById(id);

        regionService.update(region, updatedRegion);

        Map<String, Object> body = new HashMap<>();
        body.put("status", "updated");

        return ResponseEntity.ok(body);
    }
}
