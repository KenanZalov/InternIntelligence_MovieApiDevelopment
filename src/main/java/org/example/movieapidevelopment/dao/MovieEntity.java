package org.example.movieapidevelopment.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private int releaseYear;
    private double imdbRating;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private GenreEntity genre;



}
