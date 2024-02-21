package io.guessmovie.guessmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.guessmovie.guessmovieapp.models.GenreModel;

@Repository
public interface GenreRepository extends JpaRepository<GenreModel, Long>{
    
}
