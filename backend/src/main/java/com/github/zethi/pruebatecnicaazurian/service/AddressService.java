package com.github.zethi.pruebatecnicaazurian.service;

import com.github.zethi.pruebatecnicaazurian.entity.Address;
import com.github.zethi.pruebatecnicaazurian.entity.City;
import com.github.zethi.pruebatecnicaazurian.entity.Province;
import com.github.zethi.pruebatecnicaazurian.entity.Region;
import com.github.zethi.pruebatecnicaazurian.exception.AddressNotFoundException;
import com.github.zethi.pruebatecnicaazurian.exception.RegionNotFoundException;
import com.github.zethi.pruebatecnicaazurian.repository.AddressRepository;
import com.github.zethi.pruebatecnicaazurian.request.CreateAddressRequest;
import com.github.zethi.pruebatecnicaazurian.request.CreateCityRequest;
import com.github.zethi.pruebatecnicaazurian.util.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getById(long id) throws AddressNotFoundException {
        return this.addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
    }

    public Address[] getAll() {
        return IterableUtils.iteratorToArray(this.addressRepository.findAll().spliterator(), Address[]::new);
    }

    public Address create(CreateAddressRequest createAddressRequest, City city) {
        Address address = new Address();

        address.setCity(city);
        address.setStreetName(createAddressRequest.streetName());
        address.setNumber(createAddressRequest.number());

        this.addressRepository.save(address);

        return address;
    }

    public void deleteById(long id) {
        this.addressRepository.deleteById(id);
    }

    public void update(Address originalAddress, Address updatedAddress) {
        originalAddress.setNumber(updatedAddress.getNumber());
        originalAddress.setCity(updatedAddress.getCity());
        originalAddress.setNumber(updatedAddress.getNumber());

        this.addressRepository.save(originalAddress);
    }
}
