package com.ifgoiano.topfilmes.api.controller;

import com.ifgoiano.topfilmes.api.dto.list.ListAddMovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListIDRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListRequestDTO;
import com.ifgoiano.topfilmes.api.dto.list.ListResponseDTO;
import com.ifgoiano.topfilmes.api.dto.user.UserResponseDTO;
import com.ifgoiano.topfilmes.api.mapper.ListMapper;
import com.ifgoiano.topfilmes.domain.service.ListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lista")
public class ListController {

    @Autowired
    private ListService service;

    // USER

    @Operation(summary = "Cria uma lista", description = "Cria uma lista de filmes para o usuário", method = "POST", responses = {
            @ApiResponse(description = "Lista criada com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao criar lista!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid ListRequestDTO listRequestDTO){
        return new ResponseEntity<ListResponseDTO>(ListMapper.convertListEntityToListResponseDTO(service.add(ListMapper.convertListRequestDTOToListEntity(listRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Adiciona Filme", description = "Adiciona um filme a lista do usuário", method = "POST", responses = {
            @ApiResponse(description = "Filme adicionado a lista com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao adicionar filme a lista!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/add-movie-to-list")
    public ResponseEntity<?> addMovieToList(@RequestBody @Valid ListAddMovieRequestDTO listAddMovieRequestDTO){
        return new ResponseEntity<ListResponseDTO>(ListMapper.convertListEntityToListResponseDTO(service.addMovieToList(listAddMovieRequestDTO)), HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna as listas", description = "Retorna a lista de filmes para o usuário", method = "GET", responses = {
            @ApiResponse(description = "Listas listadas com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao listar listas!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/list-all")
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(ListMapper.convertListOfListEntityToListOfListResponseDTO(service.listAll()));
    }

    @Operation(summary = "Busca uma lista", description = "Retorna a lista por id", method = "GET", responses = {
            @ApiResponse(description = "Lista encontrada!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao buscar lista!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/find/{idList}")
    public ResponseEntity<?> listById(@PathVariable("idList") Long idList){
        return ResponseEntity.ok(ListMapper.convertListEntityToListResponseDTO(service.searchById(idList)));
    }

    // ADMIN


}
