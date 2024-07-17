package com.ifgoiano.demo.api.controller;

import com.ifgoiano.demo.api.dto.comentario.CommentRequestDTO;
import com.ifgoiano.demo.api.dto.comentario.CommentResponseDTO;
import com.ifgoiano.demo.api.mapper.CommentMapper;
import com.ifgoiano.demo.domain.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping(value = "/comentarios", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Comentário")
public class CommentController {

    @Autowired
    private CommentService service;

    // USER

    @Operation(summary = "Comentar filme", description = "Comenta em um filme", method = "POST", responses = {
            @ApiResponse(description = "Comentário foi realizado com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao comentar em filme!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping("/comentar")
    public ResponseEntity<?> comentarFilme(@RequestBody @Valid CommentRequestDTO commentRequestDTO) {
        return new ResponseEntity<CommentResponseDTO>(CommentMapper.converterComentarioEntidadeEmComentarioResponseDTO(service.comment(CommentMapper.converterComentarioRequestDTOEmComentarioEntidade(commentRequestDTO))), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista comentários", description = "Lista todos os comentários de um determinado filme cadastrado", method = "GET", responses = {
            @ApiResponse(description = "Comentários do filme listados com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao listar comentários do filme!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/listar-comentarios")
    public ResponseEntity<?> listarComentarios() {
        return ResponseEntity.ok(CommentMapper.converterListaDeComentarioEntidadeParaListaDeComentarioResponseDTO(service.listAll()));
    }

    // ADMIN

    @Operation(summary = "Deletar comentário de filme", description = "Deleta um comentário feito sobre um filme", method = "POST", responses = {
            @ApiResponse(description = "Comentário foi deletado com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao deletar comentário do filme!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @DeleteMapping("/deletar-comentario/{idComentario}")
    public ResponseEntity<?> deletarComentarioDoFilme(@PathVariable("idComentario") @Valid @NotNull(message = "Informe o id do comentário!") Long idComentario) {
        service.deleteById(idComentario);
        return ResponseEntity.noContent().build();
    }


}
