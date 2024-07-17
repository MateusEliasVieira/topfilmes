package com.ifgoiano.demo.api.controller;

import com.ifgoiano.demo.api.dto.sessao.SessionRequestDTO;
import com.ifgoiano.demo.api.dto.sessao.SessionResponseDTO;
import com.ifgoiano.demo.api.mapper.SessionMapper;
import com.ifgoiano.demo.domain.service.SessionService;
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
        return ResponseEntity.ok(SessionMapper.converterListaDeSessaoEntidadeParaListaDeSessaoResponseDTO(service.listAll()));
    }

    // ADMIN

    @Operation(summary = "Cadastro de uma nova sessão", description = "Cadastra uma nova sessão", method = "POST", responses = {
            @ApiResponse(description = "Cadastro realizado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SessionResponseDTO.class))),
            @ApiResponse(description = "Houve um erro ao cadastrar uma nova sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid SessionRequestDTO sessionRequestDTO){
        return new ResponseEntity<SessionResponseDTO>(SessionMapper.converterCinemaEntidadeEmCinemaResponseDTO(service.add(SessionMapper.converterSessaoRequestDTOEmSessaoEntidade(sessionRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Deleta sessões", description = "Realiza a remoção de sessões pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Remoção realizada com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Não foi possível deletar a sessão!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/delete-session/{idSession}")
    public ResponseEntity<?> delete(@PathVariable("idSession") @Valid @NotNull(message = "Informe o id da sessão!") Long idSession){
        service.deleteById(idSession);
        return ResponseEntity.noContent().build();
    }


}
