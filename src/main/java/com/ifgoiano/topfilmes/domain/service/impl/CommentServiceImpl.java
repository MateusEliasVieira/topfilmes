package com.ifgoiano.topfilmes.domain.service.impl;

import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.model.Comment;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.model.User;
import com.ifgoiano.topfilmes.domain.repository.CommentRepository;
import com.ifgoiano.topfilmes.domain.repository.MovieRepository;
import com.ifgoiano.topfilmes.domain.repository.UserRepository;
import com.ifgoiano.topfilmes.domain.service.CommentService;
import com.ifgoiano.topfilmes.domain.service.MovieService;
import com.ifgoiano.topfilmes.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Transactional(readOnly = false)
    @Override
    public Comment comment(Comment comment) {
        Movie movie = movieService.searchById(comment.getMovie().getIdMovie());
        User user = userService.searchById(comment.getUser().getIdUser());
        comment.setMovie(movie);
        comment.setUser(user);
        
        return repository.save(comment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> listById(Long idMovie) {
        List<Comment> comments = movieService.searchById(idMovie).getComments();
        return comments;
    }

    @Transactional(readOnly = true)
    @Override
    public Comment searchById(Long idComment) {
        return repository.findById(idComment).orElseThrow(() -> new BusinessRulesException("Não existe comentário com id " + idComment + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idComment) {
        try {
            searchById(idComment);
            repository.deleteById(idComment);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe comentário com id " + idComment + " para ser deletado!");
        }
    }

}
