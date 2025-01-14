package org.example.movieapidevelopment.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {
    private String title;
    private String director;
    private int releaseYear;
    private double imdbRating;
    private Long genreId;

}
