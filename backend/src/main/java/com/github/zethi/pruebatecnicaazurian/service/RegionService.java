package com.github.zethi.pruebatecnicaazurian.service;

import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.RegionNotFoundException;
import com.github.zethi.pruebatecnicaazurian.repository.RegionRepository;
import com.github.zethi.pruebatecnicaazurian.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region getById(long id) throws RegionNotFoundException {
        return this.regionRepository.findById(id).orElseThrow(RegionNotFoundException::new);
    }

    public Region[] getAll() {
        return IterableUtils.iteratorToArray(this.regionRepository.findAll().spliterator(), Region[]::new);
    }

    public void create(Region region) {
        this.regionRepository.save(region);
    }

    public void deleteById(long id) {
        this.regionRepository.deleteById(id);
    }

    public void update(Region originalRegion, Region updatedRegion) {
        originalRegion.setName(updatedRegion.getName());
        originalRegion.setOrdinal(updatedRegion.getOrdinal());

        this.regionRepository.save(originalRegion);
    }
}
