package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.user.UserWithIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
import com.ifgoiano.topfilmes.domain.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User convertUserRequestDTOToUserEntity(UserRequestDTO userRequestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userRequestDTO, User.class);
    }

    public static User convertUserWithIDRequestDTOToUserEntity(UserWithIDRequestDTO userWithIDRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userWithIDRequestDTO, User.class);
    }

    public static UserResponseDTO convertUserEntityToUserResponseDTO(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public static List<UserResponseDTO> convertListUsersEntityToListUserResponseDTO(List<User> listUser) {
        ModelMapper modelMapper = new ModelMapper();
        return listUser.stream()
                .map(user-> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
