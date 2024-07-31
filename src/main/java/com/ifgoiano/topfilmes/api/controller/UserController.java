package com.ifgoiano.topfilmes.api.controller;

import com.ifgoiano.topfilmes.api.dto.user.UserWithIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserRequestDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
import com.ifgoiano.topfilmes.api.mapper.UserMapper;
import com.ifgoiano.topfilmes.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Usuario")
public class UserController {

    @Autowired
    private UserService service;

    // USER
    @Operation(summary = "Cadastra usuários", description = "Realiza cadastros de novos usuários", method = "POST", responses = {
            @ApiResponse(description = "Usuário cadastrado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Erro ao cadastrar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = UserMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.add(UserMapper.converterUsuarioRequestDTOEmUsuarioEntidade(userRequestDTO)));
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.CREATED);
    }

    // ADMIN

    @Operation(summary = "Atualiza usuários", description = "Realiza a atualização de um usuário", method = "PUT", responses = {
            @ApiResponse(description = "Usuário atualizado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Erro ao atualizar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UserWithIDRequestDTO userWithIDRequestDTO) {
        return ResponseEntity.ok(UserMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.update(UserMapper.converterUsuarioComIDRequestDTOEmUsuarioEntidade(userWithIDRequestDTO))));
    }

    @Operation(summary = "Deleta usuários", description = "Realiza a remoção de um usuário por id", method = "DELETE", responses = {
            @ApiResponse(description = "Usuário deletado com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao deletar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteById(@PathVariable("idUser") @Valid @NotNull(message = "Informe o id do usuário!") Long idUser) {
        service.deleteById(idUser);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários", description = "Realiza a busca de um usuário por id", method = "GET", responses = {
            @ApiResponse(description = "Usuário encontrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Erro ao encontrar usuário!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/find/{idUser}")
    public ResponseEntity<?> searchById(@PathVariable("idUser") @Valid @NotNull(message = "Informe o id do usuário!") Long idUser) {
        return ResponseEntity.ok(UserMapper.converterUsuarioEntidadeParaUsuarioResponseDTO(service.searchById(idUser)));
    }

    @Operation(summary = "Lista usuários", description = "Realiza a listagem de todos os usuários cadastrados", method = "GET", responses = {
            @ApiResponse(description = "Usuários listados com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Erro ao listar usuários!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("list-all")
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(UserMapper.converterListaDeUsuariosEntidadeParaListaDeUsuarioResponseDTO(service.listAll()));
    }

}
