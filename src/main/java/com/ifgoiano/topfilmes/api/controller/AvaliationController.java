package com.ifgoiano.topfilmes.api.controller;

import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationRequestDTO;
import com.ifgoiano.topfilmes.api.dto.avaliation.AvaliationResponseDTO;
import com.ifgoiano.topfilmes.api.mapper.AvaliationMapper;
import com.ifgoiano.topfilmes.domain.service.AvaliationService;
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
@RequestMapping(path = "/avaliation", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Avaliação")
public class AvaliationController {

    @Autowired
    private AvaliationService service;

    // USER

    @Operation(summary = "Cadastra avaliação", description = "Realiza o cadastro de avaliação", method = "POST", responses = {
            @ApiResponse(description = "Avaliação realizada com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AvaliationResponseDTO.class))),
            @ApiResponse(description = "Não foi possível cadastrar a avaliação!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid AvaliationRequestDTO avaliationRequestDTO){
        return new ResponseEntity<AvaliationResponseDTO>(AvaliationMapper.convertAvaliationEntityToAvaliationResponseDTO(service.add(AvaliationMapper.convertAvaliationRequestDTOToAvaliationEntity(avaliationRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista avaliações", description = "Realiza a listagem de todas avaliações", method = "GET", responses = {
            @ApiResponse(description = "Listagem realizada com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AvaliationResponseDTO.class))),
            @ApiResponse(description = "Não foi possível listar as avaliações!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/list/{idMovie}")
    public ResponseEntity<?> listAvaliationsByMovie(@PathVariable("idMovie") Long idMovie){
        return ResponseEntity.ok(AvaliationMapper.convertListAvaliationEntityToListAvaliationResponseDTO(service.listAvaliationByMovie(idMovie)));
    }

    // ADMIN

    @Operation(summary = "Deleta uma avaliação", description = "Realiza a remoção de uma avaliação pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Remoção realizada com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Não foi possível remover uma avaliação", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/delete/{idAvaliation}")
    public ResponseEntity<?> delete(@PathVariable("idAvaliation") @Valid @NotNull(message = "O id da avaliação deve ser informado!") Long idAvaliation) {
        service.deleteById(idAvaliation);
        return ResponseEntity.noContent().build();
    }


}
