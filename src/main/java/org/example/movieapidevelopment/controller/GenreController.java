package org.example.movieapidevelopment.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieapidevelopment.dto.request.GenreRequestDto;
import org.example.movieapidevelopment.dto.response.GenreResponseDto;
import org.example.movieapidevelopment.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/addGenre")
    public String addGenre(@RequestBody GenreRequestDto genreRequestDto) {
        return genreService.addGenre(genreRequestDto);
    }

    @GetMapping("/getAllGenres")
    public List<GenreResponseDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PutMapping("/updateGenre")
    public String updateGenre( @RequestParam Long id,
                               @RequestBody GenreRequestDto genreRequestDto) {
        return genreService.updateGenre(id, genreRequestDto);
    }

    @DeleteMapping("/deleteGenre")
    public String deleteGenreById(@RequestParam Long genreId) {
        return genreService.deleteGenreById(genreId);
    }
}
