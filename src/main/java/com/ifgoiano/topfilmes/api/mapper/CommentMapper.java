package com.ifgoiano.topfilmes.api.mapper;

import com.ifgoiano.topfilmes.api.dto.comment.CommentRequestDTO;
import com.ifgoiano.topfilmes.api.dto.comment.CommentResponseDTO;
import com.ifgoiano.topfilmes.domain.model.Comment;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper {

    public static Comment convertCommentRequestDTOToCommentEntity(CommentRequestDTO commentRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(commentRequestDTO, Comment.class);
    }

    public static CommentResponseDTO convertCommentEntityToCommentResponseDTO(Comment comment){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comment, CommentResponseDTO.class);
    }
    public static List<CommentResponseDTO> convertListCommentEntityToListCommentResponseDTO(List<Comment> listComment) {
        ModelMapper modelMapper = new ModelMapper();
        return listComment.stream()
                .map(comment -> modelMapper.map(comment, CommentResponseDTO.class))
                .collect(Collectors.toList());
    }
}
