package com.ifgoiano.demo.api.controller;

import com.ifgoiano.demo.api.dto.lista.ListRequestDTO;
import com.ifgoiano.demo.api.dto.lista.ListResponseDTO;
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
@RequestMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lista")
public class ListController {

    @Autowired
    private ListService service;

    // USER

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid ListRequestDTO listRequestDTO){
        return new ResponseEntity<ListResponseDTO>(ListMapper.converterListaEntidadeEmListaResponseDTO( service.add(ListMapper.converterListaRequestDTOEmListaEntidade(listRequestDTO))), HttpStatus.CREATED);
    }

    @GetMapping("/list-all")
    public ResponseEntity<?> listAll(){
        return ResponseEntity.ok(ListMapper.converterListaDeListaEntidadeParaListaDeListaResponseDTO(service.listAll()));
    }

    // ADMIN


}
