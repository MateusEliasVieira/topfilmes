package com.ifgoiano.topfilmes.domain.service;

import com.ifgoiano.topfilmes.domain.model.Comment;

import java.util.List;

public interface CommentService {

    public Comment comment(Comment comment);

    public List<Comment> listAll();

    public Comment searchById(Long idComment);

    public void deleteById(Long idComment);
}
