package com.ifgoiano.topfilmes.api.controller;

import com.ifgoiano.topfilmes.api.dto.filme.MovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.filme.MovieResponseDTO;
import com.ifgoiano.topfilmes.api.mapper.MovieMapper;
import com.ifgoiano.topfilmes.api.message.MessageResponse;
import com.ifgoiano.topfilmes.domain.service.MovieService;
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
@RequestMapping(path = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Filme")
public class MovieController {

    @Autowired
    private MovieService service;

    // USER

    @GetMapping("/list")
    @Operation(summary = "Lista filmes", description = "Lista todos os filmes cadastrados", method = "GET", responses = {
            @ApiResponse(description = "Filmes listados com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Erro ao listar filmes!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(MovieMapper.converterListaDeFilmeEntidadeParaListaDeFilmeResponseDTO(service.listAll()));
    }

    // ADMIN

    @PostMapping("/add")
    @Operation(summary = "Cadastra filmes", description = "Salva um novo filme", method = "POST", responses = {
            @ApiResponse(description = "Filme cadastrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Erro ao cadastrar filme!", responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<?> add(@RequestBody @Valid MovieRequestDTO movieRequestDTO) {
        return new ResponseEntity<MovieResponseDTO>(MovieMapper.converterFilmeEntidadeParaFilmeResponseDTO(service.add(MovieMapper.converterFilmeRequestDTOParaFilmeEntidade(movieRequestDTO))), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idMovie}")
    @Operation(summary = "Deleta filmes", description = "Deleta um filme pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Filme deletado com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao deletar filme!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<?> delete(@PathVariable("idMovie") @Valid @NotNull(message = "Informe o id do filme!") Long idMovie) {
        service.deleteById(idMovie);
        return ResponseEntity.noContent().build();
    }

}
