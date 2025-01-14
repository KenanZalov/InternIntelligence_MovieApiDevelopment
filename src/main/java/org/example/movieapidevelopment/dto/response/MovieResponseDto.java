package org.example.movieapidevelopment.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponseDto {
    private Long id;
    private String title;
    private String director;
    private int releaseYear;
    private double imdbRating;
    private GenreResponseDto genre;
}
