package org.example.movieapidevelopment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movieapidevelopment.dao.GenreEntity;
import org.example.movieapidevelopment.dao.MovieEntity;
import org.example.movieapidevelopment.dto.request.MovieRequestDto;
import org.example.movieapidevelopment.dto.response.MovieResponseDto;
import org.example.movieapidevelopment.repository.GenreRepository;
import org.example.movieapidevelopment.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public String addMovie(MovieRequestDto movieRequestDto) {
        log.info("Request to add movie: {}", movieRequestDto.getTitle());
        if (movieRepository.existsByTitle(movieRequestDto.getTitle())) {
            log.warn("Movie already exists: {}", movieRequestDto.getTitle());
            return "Movie already exists!";
        }
        GenreEntity genre = genreRepository.findById(movieRequestDto.getGenreId())
                .orElseThrow(() -> {
                    log.error(" Genre not found with ID: {} ", movieRequestDto.getGenreId());
                    return new RuntimeException("Genre not found!");
                });
        MovieEntity movie = modelMapper.map(movieRequestDto, MovieEntity.class);
        movie.setGenre(genre);
        movieRepository.save(movie);
        log.info("Movie added successfully: {}", movie.getTitle());
        return "Movie added successfully!";
    }

    public List<MovieResponseDto> getAllMovies() {
        List<MovieEntity> movies = movieRepository.findAll();
        return movies
                .stream()
                .map(m -> modelMapper.map(m, MovieResponseDto.class))
                .toList();
    }

    public String updateMovie(Long movieId, MovieRequestDto movieRequestDto) {
        log.info("Request to update movie with ID: {}", movieId);
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(() -> {
                    log.error("Movie not found with ID: {}", movieId);
                    return new RuntimeException("Movie not found!");
                });
        GenreEntity genre = genreRepository.findById(movieRequestDto.getGenreId())
                .orElseThrow(() -> {
                    log.error("Genre not found with ID: {}", movieRequestDto.getGenreId());
                    return new RuntimeException("Genre not found!");
                });
        movie.setGenre(genre);
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDirector(movieRequestDto.getDirector());
        movie.setReleaseYear(movieRequestDto.getReleaseYear());
        movie.setImdbRating(movieRequestDto.getImdbRating());
        movieRepository.save(movie);
        log.info("Movie updated successfully: {}", movie.getTitle());
        return "Movie updated successfully!";
    }

    public String deleteMovieById(Long movieId) {
        log.info("Request to delete movie with ID: {}", movieId);
        if (!movieRepository.existsById(movieId)){
            log.warn("Movie not found with ID: {}", movieId);
            return "Movie not found!";
        }
        movieRepository.deleteById(movieId);
        log.info("Movie deleted successfully with ID: {}", movieId);
        return "Movie deleted successfully!";
    }

    public MovieResponseDto getMovieByTitle(String title) {
        log.info("Fetching movie by title: {}", title);
        MovieEntity movie = movieRepository.findByTitle(title);
        if (movie == null) {
            log.error("Movie not found with title: {}", title);
            throw new RuntimeException("Movie not found!");
        }
        log.info("Movie found: {}", title);
        return modelMapper.map(movie, MovieResponseDto.class);
    }

    public List<MovieResponseDto> getMoviesByGenre(String genreName) {
        log.info("Fetching movies by genre: {}", genreName);
        List<MovieEntity> movies = movieRepository.findByGenreName(genreName);
        if (movies == null) {
            log.warn("No movies found in genre: {}", genreName);
            throw new RuntimeException("There are no movies in this genre!");
        }
        log.info("Fetched {} movies in genre: {}", movies.size(), genreName);
        return movies
                .stream()
                .map(m -> modelMapper.map(m, MovieResponseDto.class))
                .toList();
    }

}
