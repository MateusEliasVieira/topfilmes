package com.ifgoiano.demo.domain.service.impl;

import com.ifgoiano.demo.domain.domainException.BusinessRulesException;
import com.ifgoiano.demo.domain.model.Comment;
import com.ifgoiano.demo.domain.model.Movie;
import com.ifgoiano.demo.domain.model.User;
import com.ifgoiano.demo.domain.repository.CommentRepository;
import com.ifgoiano.demo.domain.repository.MovieRepository;
import com.ifgoiano.demo.domain.repository.UserRepository;
import com.ifgoiano.demo.domain.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository repository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = false)
    @Override
    public Comment comment(Comment comment) {
        Comment commentSalvo = repository.save(comment);
        Movie movie = movieRepository.findById(commentSalvo.getMovie().getIdMovie()).orElseThrow(() -> new BusinessRulesException("Não foi possível vincular o filme ao comentário, pois o id do filme é inválido!"));
        User user = userRepository.findById(commentSalvo.getUser().getIdUser()).orElseThrow(() -> new BusinessRulesException("Não foi possível vincular o usuário ao comentário, pois o id do usuário é inválido!"));
        commentSalvo.setMovie(movie);
        commentSalvo.setUser(user);
        return commentSalvo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> listAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Comment searchById(Long idComentario) {
        return repository.findById(idComentario).orElseThrow(() -> new BusinessRulesException("Não existe comentário com id " + idComentario + "!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteById(Long idComentario) {
        try {
            searchById(idComentario);
            repository.deleteById(idComentario);
        } catch (BusinessRulesException businessRulesException) {
            throw new BusinessRulesException("Não existe comentário com id " + idComentario + " para ser deletado!");
        }
    }

}
