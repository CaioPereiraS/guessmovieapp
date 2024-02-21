package io.guessmovie.guessmovieapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.guessmovie.guessmovieapp.dtos.GenreDto;
import io.guessmovie.guessmovieapp.dtos.MovieDto;
import io.guessmovie.guessmovieapp.models.GenreModel;
import io.guessmovie.guessmovieapp.models.MovieModel;
import io.guessmovie.guessmovieapp.repository.GenreRepository;
import io.guessmovie.guessmovieapp.repository.MovieRepository;
import io.guessmovie.guessmovieapp.services.exceptions.DatabaseExeption;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class MovieServices {
    
    @Autowired
    private MovieRepository MovieRepository;
    
    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public Page<MovieDto> findAllPaged(Pageable pageRequest){
        Page<MovieModel> list = MovieRepository.findAll(pageRequest);
        return list.map(x -> new MovieDto(x));
    }

    @Transactional(readOnly = true)
    public List<MovieDto> findAll() {
        List<MovieModel> list = MovieRepository.findAll();
        return list.stream().map(x -> new MovieDto(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MovieDto findById(Long id) {
        Optional<MovieModel> obj = MovieRepository.findById(id);
        MovieModel entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new MovieDto(entity);
    }

    @Transactional
    public MovieDto insert(MovieDto dto) {
        MovieModel entity = new MovieModel();
        copyDtoToEntity(dto, entity);
        entity = MovieRepository.save(entity);
        return new MovieDto(entity);
    }

    @Transactional
    public MovieDto update(Long id, MovieDto dto) {
        MovieModel entity = MovieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        copyDtoToEntity(dto, entity);
        entity = MovieRepository.save(entity);
        return new MovieDto(entity);
    }

    @Transactional
    public void delete(Long id) {
        try {
            MovieRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Id not found " + id);
        } catch(EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("Id not found " + id, 0);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseExeption("Integrity violation");
        }
    }

    private void copyDtoToEntity(MovieDto dto, MovieModel entity) {
        entity.setTitle(dto.getTitle());
        entity.setYear(dto.getYear());
        entity.setDirector(dto.getDirector());
        entity.setPoster(dto.getPoster());
        entity.setRating_imdb(dto.getRating_imdb());
        entity.getGenres().clear();
        for (GenreDto genreDto : dto.getGenres()) {
            GenreModel genre = genreRepository.getReferenceById(genreDto.getId());
            entity.getGenres().add(genre);
        }
    }

}
