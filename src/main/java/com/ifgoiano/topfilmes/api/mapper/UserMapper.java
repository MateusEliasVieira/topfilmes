package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.user.UserWithIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
import com.ifgoiano.topfilmes.domain.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User converterUsuarioRequestDTOEmUsuarioEntidade(UserRequestDTO userRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRequestDTO, User.class);
    }

    public static User converterUsuarioComIDRequestDTOEmUsuarioEntidade(UserWithIDRequestDTO userWithIDRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userWithIDRequestDTO, User.class);
    }

    public static UserResponseDTO converterUsuarioEntidadeParaUsuarioResponseDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public static List<UserResponseDTO> converterListaDeUsuariosEntidadeParaListaDeUsuarioResponseDTO(List<User> listaUser) {
        ModelMapper modelMapper = new ModelMapper();
        return listaUser.stream()
                .map(usuario -> modelMapper.map(usuario, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
