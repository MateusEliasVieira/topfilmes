package com.ifgoiano.demo.api.mapper;

import com.ifgoiano.demo.api.dto.comentario.ComentarioRequestDTO;
import com.ifgoiano.demo.api.dto.comentario.ComentarioResponseDTO;
import com.ifgoiano.demo.domain.model.Comment;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static Comment converterComentarioRequestDTOEmComentarioEntidade(ComentarioRequestDTO comentarioRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comentarioRequestDTO, Comment.class);
    }

    public static ComentarioResponseDTO converterComentarioEntidadeEmComentarioResponseDTO(Comment comment){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comment, ComentarioResponseDTO.class);
    }
    public static List<ComentarioResponseDTO> converterListaDeComentarioEntidadeParaListaDeComentarioResponseDTO(List<Comment> listaComment) {
        ModelMapper modelMapper = new ModelMapper();
        return listaComment.stream()
                .map(comentario -> modelMapper.map(comentario, ComentarioResponseDTO.class))
                .collect(Collectors.toList());
    }
}
