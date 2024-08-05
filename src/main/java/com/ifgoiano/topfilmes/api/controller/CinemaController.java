package com.ifgoiano.topfilmes.api.controller;

import com.ifgoiano.topfilmes.api.dto.cinema.CinemaRequestDTO;
import com.ifgoiano.topfilmes.api.dto.cinema.CinemaResponseDTO;
import com.ifgoiano.topfilmes.api.mapper.CinemaMapper;
import com.ifgoiano.topfilmes.domain.service.CinemaService;
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
@RequestMapping(path = "/cinema", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Cinema")
public class CinemaController {

    @Autowired
    private CinemaService service;

    // USER
    @Operation(summary = "Lista cinemas", description = "Realiza a listagem de todos cinemas", method = "GET", responses = {
            @ApiResponse(description = "Listagem realizada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CinemaResponseDTO.class))),
            @ApiResponse(description = "Não foi possível listar os cinemas!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/list-all")
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(CinemaMapper.convertListCinemaEntityToListCinemaResponseDTO(service.listAll()));
    }

    // ADMIN

    @Operation(summary = "Cadastra cinema", description = "Realiza o cadastro de cinema", method = "POST", responses = {
            @ApiResponse(description = "Cadastro realizado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CinemaResponseDTO.class))),
            @ApiResponse(description = "Não foi possível cadastrar o cinema!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid CinemaRequestDTO cinemaRequestDTO){
        return new ResponseEntity<CinemaResponseDTO>(CinemaMapper.convertCinemaEntityToCinemaResponseDTO(service.add(CinemaMapper.convertCinemaRequestDTOToCinemaEntity(cinemaRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza cinema", description = "Atualiza o cadastro de cinema", method = "PUT", responses = {
            @ApiResponse(description = "Cadastro atualizado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CinemaResponseDTO.class))),
            @ApiResponse(description = "Não foi possível atualizar o cinema!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid CinemaRequestDTO cinemaRequestDTO){
        return new ResponseEntity<CinemaResponseDTO>(CinemaMapper.convertCinemaEntityToCinemaResponseDTO(service.update(CinemaMapper.convertCinemaRequestDTOToCinemaEntity(cinemaRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista cinema", description = "Lista um cinema por id", method = "GET", responses = {
            @ApiResponse(description = "Cinema encontrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CinemaResponseDTO.class))),
            @ApiResponse(description = "Não foi possível encontrar o cinema!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/list/{idCinema}")
    public ResponseEntity<?> update(@PathVariable("idCinema") Long idCinema){
        return new ResponseEntity<CinemaResponseDTO>(CinemaMapper.convertCinemaEntityToCinemaResponseDTO(service.searchById(idCinema)), HttpStatus.OK);
    }

    @Operation(summary = "Deleta um cinema", description = "Realiza a remoção de um cinema pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Remoção realizada com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Não foi possível remover um filme", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/delete/{idCinema}")
    public ResponseEntity<?> delete(@PathVariable("idCinema") @Valid @NotNull(message = "Informe o id do cinema!") Long idCinema){
        service.deleteById(idCinema);
        return ResponseEntity.noContent().build();
    }


}
