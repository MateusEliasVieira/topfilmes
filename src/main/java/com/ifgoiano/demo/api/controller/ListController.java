package com.ifgoiano.demo.api.controller;

import com.ifgoiano.demo.api.dto.lista.ListaRequestDTO;
import com.ifgoiano.demo.api.dto.lista.ListaResponseDTO;
import com.ifgoiano.demo.api.mapper.ListMapper;
import com.ifgoiano.demo.domain.service.ListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lista")
public class ListController {

    @Autowired
    private ListService service;

    // USER

    @PostMapping("/novo")
    public ResponseEntity<?> salvarLista(@RequestBody @Valid ListaRequestDTO listaRequestDTO){
        return new ResponseEntity<ListaResponseDTO>(ListMapper.converterListaEntidadeEmListaResponseDTO( service.salvarLista(ListMapper.converterListaRequestDTOEmListaEntidade(listaRequestDTO))), HttpStatus.CREATED);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<?> listarTodasListas(){
        return ResponseEntity.ok(ListMapper.converterListaDeListaEntidadeParaListaDeListaResponseDTO(service.listarTodasListas()));
    }

    // ADMIN


}
