package com.github.zethi.pruebatecnicaazurian.repository;

import com.github.zethi.pruebatecnicaazurian.entity.Province;
import com.github.zethi.pruebatecnicaazurian.entity.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProvinceRepository extends CrudRepository<Province, Long> {
    List<Province> findByRegion(Region region);
}
