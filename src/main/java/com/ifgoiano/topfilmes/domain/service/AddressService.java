package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.model.Address;

import java.util.List;

public interface AddressService {
    public Address add(Address address);

    public Address update(Address address);

    public List<Address> listAll();

    public Address searchById(Long idEndereco);

    public void deleteById(Long idEndereco);
}
