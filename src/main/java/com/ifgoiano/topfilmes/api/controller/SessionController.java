package com.ifgoiano.topfilmes.api.controller;

import com.ifgoiano.topfilmes.api.dto.session.SessionRequestDTO;
import com.ifgoiano.topfilmes.api.dto.session.SessionResponseDTO;
import com.ifgoiano.topfilmes.api.mapper.SessionMapper;
import com.ifgoiano.topfilmes.domain.service.SessionService;
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
@RequestMapping(path = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sessao")
public class SessionController {

    @Autowired
    private SessionService service;

    // USER

    @Operation(summary = "Lista sessões", description = "Realiza a listagem de todas sessões e seus filmes relacionados", method = "GET", responses = {
            @ApiResponse(description = "Listagem realizada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionResponseDTO.class))),
            @ApiResponse(description = "Não foi possível listar as sessões!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/list-all")
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(SessionMapper.convertListSessionEntityToListSessionResponseDTO(service.listAll()));
    }

    @Operation(summary = "Lista sessão", description = "Realiza a listagem de uma sessão pelo seu código de sessão e seus filmes relacionados", method = "GET", responses = {
            @ApiResponse(description = "Listagem realizada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionResponseDTO.class))),
            @ApiResponse(description = "Não foi possível listar a sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/list/{codSession}")
    public ResponseEntity<?> listByCodSession(@PathVariable("codSession") Long codSession){
        return ResponseEntity.ok(service.searchByCodSession(codSession));
    }

    // ADMIN

    @Operation(summary = "Cadastro de uma nova sessão", description = "Cadastra uma nova sessão", method = "POST", responses = {
            @ApiResponse(description = "Cadastro realizado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionResponseDTO.class))),
            @ApiResponse(description = "Houve um erro ao cadastrar uma nova sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid SessionRequestDTO sessionRequestDTO){
        return new ResponseEntity<SessionResponseDTO>(SessionMapper.convertSessionEntityToSessionResponseDTO(service.add(SessionMapper.convertSessionRequestDTOToSessionEntity(sessionRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza uma sessão", description = "Atualiza uma sessão existente", method = "PUT", responses = {
            @ApiResponse(description = "Atualização realizada com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionResponseDTO.class))),
            @ApiResponse(description = "Houve um erro ao atualizar a sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid SessionRequestDTO sessionRequestDTO){
        return new ResponseEntity<SessionResponseDTO>(SessionMapper.convertSessionEntityToSessionResponseDTO(service.update(SessionMapper.convertSessionRequestDTOToSessionEntity(sessionRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta sessões", description = "Realiza a remoção de sessões pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Remoção realizada com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Não foi possível deletar a sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/delete/{idSession}")
    public ResponseEntity<?> delete(@PathVariable("idSession") @Valid @NotNull(message = "Informe o id da sessão!") Long idSession){
        service.deleteById(idSession);
        return ResponseEntity.noContent().build();
    }


}
