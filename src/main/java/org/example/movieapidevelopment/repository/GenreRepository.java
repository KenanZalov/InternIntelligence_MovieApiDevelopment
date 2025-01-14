package org.example.movieapidevelopment.repository;

import org.example.movieapidevelopment.dao.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    boolean existsByName(String GenreName);

}
