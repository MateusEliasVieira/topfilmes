package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Address;
import com.ifgoiano.demo.domain.repository.AddressRepository;
import com.ifgoiano.demo.domain.service.AddressService;
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
    public Address salvarEndereco(Address address) {
        return repository.save(address);
    }

    @Transactional(readOnly = false)
    @Override
    public Address atualizarEndereco(Address address) {
        return repository.save(address);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Address> listarTodosEnderecos() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Address buscarEnderecoPorId(Long idEndereco) {
        return repository.findById(idEndereco).orElseThrow(() -> new BusinessRulesException("Não existe endereço com id " + idEndereco + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deletarEnderecoPorId(Long idEndereco) {
        try {
            buscarEnderecoPorId(idEndereco);
            repository.deleteById(idEndereco);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe endereço com id " + idEndereco + " para ser deletado!");
        }
    }

}
