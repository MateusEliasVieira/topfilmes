package com.ifgoiano.topfilmes.api.controller;

import com.ifgoiano.topfilmes.api.dto.movie.MovieRequestDTO;
import com.ifgoiano.topfilmes.api.dto.movie.MovieResponseDTO;
import com.ifgoiano.topfilmes.api.mapper.MovieMapper;
import com.ifgoiano.topfilmes.api.message.MessageResponse;
import com.ifgoiano.topfilmes.domain.domainException.BusinessRulesException;
import com.ifgoiano.topfilmes.domain.enums.Gender;
import com.ifgoiano.topfilmes.domain.model.Movie;
import com.ifgoiano.topfilmes.domain.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/movie", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Filme")
public class MovieController {

    @Autowired
    private MovieService service;

    // USER

    @GetMapping("/list-all")
    @Operation(summary = "Lista filmes", description = "Lista todos os filmes cadastrados", method = "GET", responses = {
            @ApiResponse(description = "Filmes listados com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Erro ao listar filmes!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(MovieMapper.convertListMovieEntityToListMovieResponseDTO(service.listAll()));
    }

    @GetMapping("/list/id/{idMovie}")
    @Operation(summary = "Busca filme por ID", description = "Busca um filme específico por seu ID", method = "GET", responses = {
            @ApiResponse(description = "Filme encontrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Filme não encontrado!", responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable("idMovie") Long idMovie) {
        // Busca o filme pelo ID
        Movie movieEntity = service.searchById(idMovie);
        if (movieEntity == null) {
            return ResponseEntity.notFound().build();
        }
        MovieResponseDTO movie = MovieMapper.convertMovieEntityToMovieResponseDTO(movieEntity);

        // Cria um link para o próprio filme
        Link selfLink = linkTo(methodOn(MovieController.class).getMovieById(idMovie)).withSelfRel();
        movie.add(selfLink);

        // Busca todos os filmes do mesmo gênero
        List<Movie> moviesWithSameGenre = service.searchByGender(movie.getGender().toString());
        for (Movie sameGenreMovie : moviesWithSameGenre) {
            if (!sameGenreMovie.getIdMovie().equals(idMovie)) { // Evita adicionar o próprio filme
                MovieResponseDTO sameGenreMovieDTO = MovieMapper.convertMovieEntityToMovieResponseDTO(sameGenreMovie);
                Link movieLink = linkTo(methodOn(MovieController.class).getMovieById(sameGenreMovie.getIdMovie()))
                        .withRel("related")
                        .withTitle(sameGenreMovieDTO.getTitle());
                movie.add(movieLink);
            }
        }

        return ResponseEntity.ok(movie);
    }

    @GetMapping("/list/title/{title}")
    @Operation(summary = "Busca filme por nome", description = "Busca um filme específico por seu nome", method = "GET", responses = {
            @ApiResponse(description = "Filme encontrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Filme não encontrado!", responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<MovieResponseDTO> getMovieByTitle(@PathVariable("title") String title) {
        // Busca o filme pelo ID
        Movie movieEntity = service.searchByTitle(title);
        if (movieEntity == null) {
            return ResponseEntity.notFound().build();
        }
        MovieResponseDTO movie = MovieMapper.convertMovieEntityToMovieResponseDTO(movieEntity);

        // Cria um link para o próprio filme
        Link selfLink = linkTo(methodOn(MovieController.class).getMovieByTitle(title)).withSelfRel();
        movie.add(selfLink);

        // Busca todos os filmes do mesmo gênero
        List<Movie> moviesWithSameGenre = service.searchByGender(movie.getGender().toString());
        for (Movie sameGenreMovie : moviesWithSameGenre) {
            if (!sameGenreMovie.getIdMovie().equals(title)) { // Evita adicionar o próprio filme
                MovieResponseDTO sameGenreMovieDTO = MovieMapper.convertMovieEntityToMovieResponseDTO(sameGenreMovie);
                Link movieLink = linkTo(methodOn(MovieController.class).getMovieById(sameGenreMovie.getIdMovie()))
                        .withRel("related")
                        .withTitle(sameGenreMovieDTO.getTitle());
                movie.add(movieLink);
            }
        }

        return ResponseEntity.ok(movie);
    }

    @GetMapping("/list/contain/{title}")
    @Operation(summary = "Busca filmes por parte do nome", description = "Busca filmes que contem parte da string informada em seu nome", method = "GET", responses = {
            @ApiResponse(description = "Filme(s) encontrado(s) com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Filme(s) não encontrado(s)!", responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<?> getMovieByContainStringInTitle(@PathVariable("title") String title) {
        List<Movie> movies = service.searchByStringInTitleMovie(title);
        List<MovieResponseDTO> moviesResponseDTO = MovieMapper.convertListMovieEntityToListMovieResponseDTO(movies);
        return ResponseEntity.ok(moviesResponseDTO);
    }

    @GetMapping("/list/gender/{gender}")
    @Operation(summary = "Busca filmes por gênero", description = "Busca filmes pelo seu gênero", method = "GET", responses = {
            @ApiResponse(description = "Filme(s) encontrado(s) com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Filme(s) não encontrado(s)!", responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<?> getMovieByGender(@PathVariable("gender") String gender) {

        List<Movie> movies = service.searchByGender(gender);
        List<MovieResponseDTO> moviesResponseDTO = MovieMapper.convertListMovieEntityToListMovieResponseDTO(movies);
        return ResponseEntity.ok(moviesResponseDTO);
    }


    // ADMIN

    @PostMapping("/add")
    @Operation(summary = "Cadastra filmes", description = "Salva um novo filme", method = "POST", responses = {
            @ApiResponse(description = "Filme cadastrado com sucesso!", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Erro ao cadastrar filme!", responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<?> add(@RequestBody @Valid MovieRequestDTO movieRequestDTO) {
        return new ResponseEntity<MovieResponseDTO>(MovieMapper.convertMovieEntityToMovieResponseDTO(service.add(MovieMapper.convertMovieRequestDTOToMovieEntity(movieRequestDTO))), HttpStatus.CREATED);
    }
    @Operation(summary = "Atualiza um filme", description = "Atualiza uma filme existente", method = "PUT", responses = {
            @ApiResponse(description = "Atualização realizada com sucesso!", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieResponseDTO.class))),
            @ApiResponse(description = "Houve um erro ao atualizar o filme!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid MovieRequestDTO movieRequestDTO){
        return new ResponseEntity<MovieResponseDTO>(MovieMapper.convertMovieEntityToMovieResponseDTO(service.update(MovieMapper.convertMovieRequestDTOToMovieEntity(movieRequestDTO))), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{idMovie}")
    @Operation(summary = "Deleta filmes", description = "Deleta um filme pelo id", method = "DELETE", responses = {
            @ApiResponse(description = "Filme deletado com sucesso!", responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Erro ao deletar filme!", responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<?> delete(@PathVariable("idMovie") @Valid @NotNull(message = "Informe o id do filme!") Long idMovie) {
        service.deleteById(idMovie);
        return ResponseEntity.noContent().build();
    }

}
