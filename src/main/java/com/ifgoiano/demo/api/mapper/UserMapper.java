package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.usuario.UsuarioComIDRequestDTO;
import com.ifgoiano.demo.api.dto.usuario.UsuarioRequestDTO;
import com.ifgoiano.demo.api.dto.usuario.UsuarioResponseDTO;
import com.ifgoiano.demo.domain.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User converterUsuarioRequestDTOEmUsuarioEntidade(UsuarioRequestDTO usuarioRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioRequestDTO, User.class);
    }

    public static User converterUsuarioComIDRequestDTOEmUsuarioEntidade(UsuarioComIDRequestDTO usuarioComIDRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioComIDRequestDTO, User.class);
    }

    public static UsuarioResponseDTO converterUsuarioEntidadeParaUsuarioResponseDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UsuarioResponseDTO.class);
    }

    public static List<UsuarioResponseDTO> converterListaDeUsuariosEntidadeParaListaDeUsuarioResponseDTO(List<User> listaUser) {
        ModelMapper modelMapper = new ModelMapper();
        return listaUser.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }
}
