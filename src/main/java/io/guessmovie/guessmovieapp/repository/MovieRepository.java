package io.guessmovie.guessmovieapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.guessmovie.guessmovieapp.models.MovieModel;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, Long>{
    
}
