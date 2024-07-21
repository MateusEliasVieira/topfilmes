package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Address;
import com.ifgoiano.topfilmes.domain.repository.AddressRepository;
import com.ifgoiano.topfilmes.domain.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Transactional(readOnly = false)
    @Override
    public Address add(Address address) {
        return repository.save(address);
    }

    @Transactional(readOnly = false)
    @Override
    public Address update(Address address) {
        return repository.save(address);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Address> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Address searchById(Long idEndereco) {
        return repository.findById(idEndereco).orElseThrow(() -> new BusinessRulesException("Não existe endereço com id " + idEndereco + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idEndereco) {
        try {
            searchById(idEndereco);
            repository.deleteById(idEndereco);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe endereço com id " + idEndereco + " para ser deletado!");
        }
    }

}
