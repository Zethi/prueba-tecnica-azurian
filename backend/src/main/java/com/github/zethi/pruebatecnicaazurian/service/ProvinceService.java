package com.github.zethi.pruebatecnicaazurian.service;

import com.github.zethi.pruebatecnicaazurian.entity.Province;
import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.ProvinceNotFoundException;
import com.github.zethi.pruebatecnicaazurian.repository.ProvinceRepository;
import com.github.zethi.pruebatecnicaazurian.request.CreateProvinceRequest;
import com.github.zethi.pruebatecnicaazurian.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }


    public Province getById(long id) throws ProvinceNotFoundException {
        return this.provinceRepository.findById(id).orElseThrow(ProvinceNotFoundException::new);
    }

    public Province[] getAll() {
        return IterableUtils.iteratorToArray(this.provinceRepository.findAll().spliterator(), Province[]::new);
    }

    public List<Province> getAllByRegion(Region region) {
        return this.provinceRepository.findByRegion(region);
    }

    public Province create(CreateProvinceRequest createProvinceRequest, Region region) {
        Province province = new Province();

        province.setName(createProvinceRequest.name());
        province.setRegion(region);

        this.provinceRepository.save(province);

        return province;
    }

    public void deleteById(long id) {
        this.provinceRepository.deleteById(id);
    }

    public void update(Province originalProvince, Province updatedProvince) {
        originalProvince.setName(updatedProvince.getName());
        originalProvince.setRegion(updatedProvince.getRegion());

        this.provinceRepository.save(originalProvince);
    }
}
