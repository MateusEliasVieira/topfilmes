package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Comment;

import java.util.List;

public interface CommentService {

    public Comment comentarFilme(Comment comment);

    public List<Comment> listarTodosComentarios();

    public Comment buscarComentarioPorId(Long idComentario);

    public void deletarComentarioPorId(Long idComentario);
}
