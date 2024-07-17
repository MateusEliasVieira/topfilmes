package com.ifgoiano.demo.domain.service;

import com.ifgoiano.demo.domain.model.Comment;

import java.util.List;

public interface CommentService {

    public Comment comment(Comment comment);

    public List<Comment> listAll();

    public Comment searchById(Long idComment);

    public void deleteById(Long idComment);
}
