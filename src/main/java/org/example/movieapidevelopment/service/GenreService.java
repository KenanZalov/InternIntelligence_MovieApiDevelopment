package org.example.movieapidevelopment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movieapidevelopment.dao.GenreEntity;
import org.example.movieapidevelopment.dao.MovieEntity;
import org.example.movieapidevelopment.dto.request.GenreRequestDto;
import org.example.movieapidevelopment.dto.response.GenreResponseDto;
import org.example.movieapidevelopment.repository.GenreRepository;
import org.example.movieapidevelopment.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreService {
    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public String addGenre(GenreRequestDto genreRequestDto) {
        log.info("Request to add genre: {}", genreRequestDto.getName());
        if (genreRepository.existsByName(genreRequestDto.getName())){
            log.info("Genre already exists: {}", genreRequestDto.getName());
            return "Genre already exists";
        }
        GenreEntity genreEntity = modelMapper.map(genreRequestDto, GenreEntity.class);
        genreRepository.save(genreEntity);
        log.info("Genre added successfully: {}", genreEntity.getName());
        return "Genre added successfully";
    }

    public List<GenreResponseDto> getAllGenres() {
        List<GenreEntity> genres = genreRepository.findAll();
        return genres
                .stream()
                .map(g -> modelMapper.map(g, GenreResponseDto.class))
                .toList();
    }

    public String updateGenre(Long genreId, GenreRequestDto genreRequestDto) {
        log.info("Request to update genre with ID: {}", genreId);
        GenreEntity genre = genreRepository.findById(genreId)
                .orElseThrow(() -> {
                    log.error(" Genre not found with ID: {} ", genreId);
                    return new RuntimeException("Genre not found");
                });
        List<MovieEntity> movieEntityByGenre = movieRepository.findMovieEntityByGenre(genre);
        GenreEntity genreEntity = modelMapper.map(genreRequestDto, GenreEntity.class);
        genreEntity.setId(genreId);
        genreEntity.setMovies(movieEntityByGenre);
        genreRepository.save(genreEntity);
        log.info("Genre updated successfully: {}", genreEntity.getName());
        return "Genre updated successfully";
    }

    public String deleteGenreById(Long genreId) {
        log.info("Request to delete genre with ID: {}", genreId);
        GenreEntity genreEntity = genreRepository.findById(genreId).orElseThrow(() -> {
            log.error("Genre not found with ID: {}", genreId);
            return new RuntimeException("Genre not found");
        });
        if (genreEntity.getMovies().isEmpty()){
        genreRepository.delete(genreEntity);
        log.info("Genre deleted successfully with ID: {}", genreId);
        return "Genre deleted successfully!";
        } else {
            log.warn("Cannot delete genre with ID {} as it has associated movies", genreId);
            return "Genre has Movie!";
        }
    }

}
