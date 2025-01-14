package org.example.movieapidevelopment.repository;

import org.example.movieapidevelopment.dao.GenreEntity;
import org.example.movieapidevelopment.dao.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    boolean existsByTitle(String title);
    boolean existsById(Long movieİd);
    MovieEntity findByTitle(String title);
    List<MovieEntity> findByGenreName(String genreName);
    List<MovieEntity> findMovieEntityByGenre(GenreEntity genre);
}
