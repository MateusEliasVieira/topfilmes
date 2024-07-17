package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Address;

import java.util.List;

public interface AddressService {
    public Address add(Address address);

    public Address update(Address address);

    public List<Address> listAll();

    public Address searchById(Long idEndereco);

    public void deleteById(Long idEndereco);
}
