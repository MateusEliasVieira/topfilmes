package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Address;

import java.util.List;

public interface AddressService {
    public Address salvarEndereco(Address address);

    public Address atualizarEndereco(Address address);

    public List<Address> listarTodosEnderecos();

    public Address buscarEnderecoPorId(Long idEndereco);

    public void deletarEnderecoPorId(Long idEndereco);
}
