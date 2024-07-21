package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.comentario.CommentRequestDTO;
import com.ifgoiano.topfilmes.api.dto.comentario.CommentResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Comment;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static Comment converterComentarioRequestDTOEmComentarioEntidade(CommentRequestDTO commentRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(commentRequestDTO, Comment.class);
    }

    public static CommentResponseDTO converterComentarioEntidadeEmComentarioResponseDTO(Comment comment){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comment, CommentResponseDTO.class);
    }
    public static List<CommentResponseDTO> converterListaDeComentarioEntidadeParaListaDeComentarioResponseDTO(List<Comment> listaComment) {
        ModelMapper modelMapper = new ModelMapper();
        return listaComment.stream()
                .map(comentario -> modelMapper.map(comentario, CommentResponseDTO.class))
                .collect(Collectors.toList());
    }
}