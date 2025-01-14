package org.example.movieapidevelopment.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieapidevelopment.dto.request.MovieRequestDto;
import org.example.movieapidevelopment.dto.response.MovieResponseDto;
import org.example.movieapidevelopment.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return movieService.addMovie(movieRequestDto);
    }

    @GetMapping("/getAllMovies")
    public List<MovieResponseDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PutMapping("/updateMovie")
    public String updateMovie(@RequestParam Long movieId,
                              @RequestBody MovieRequestDto movieRequestDto) {
        return movieService.updateMovie(movieId, movieRequestDto);
    }

    @DeleteMapping("/deleteMovieById")
    public String deleteMovieById(@RequestParam Long movieId) {
        return movieService.deleteMovieById(movieId);
    }

    @GetMapping("/getMovieByTitle")
    public MovieResponseDto getMovieByTitle(@RequestParam String title) {
        return movieService.getMovieByTitle(title);
    }

    @GetMapping("/getMoviesByGenre")
    public List<MovieResponseDto> getMoviesByGenre(@RequestParam String genre) {
        return movieService.getMoviesByGenre(genre);
    }
}
