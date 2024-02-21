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
import io.guessmovie.guessmovieapp.models.GenreModel;
import io.guessmovie.guessmovieapp.repository.GenreRepository;
import io.guessmovie.guessmovieapp.services.exceptions.DatabaseExeption;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

@Service
public class GenreServices {
    
    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public Page<GenreDto> findAllPaged(Pageable pageRequest){
        Page<GenreModel> list = genreRepository.findAll(pageRequest);
        return list.map(x -> new GenreDto(x));
    }

    @Transactional(readOnly = true)
    public List<GenreDto> findAll() {
        List<GenreModel> list = genreRepository.findAll();
        return list.stream().map(x -> new GenreDto(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GenreDto findById(Long id) {
        Optional<GenreModel> obj = genreRepository.findById(id);
        GenreModel entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new GenreDto(entity);
    }

    @Transactional
    public GenreDto insert(GenreDto dto) {
        GenreModel entity = new GenreModel();
        entity.setName(dto.getName());
        entity = genreRepository.save(entity);
        return new GenreDto(entity);
    }

    @Transactional
    public GenreDto update(Long id, GenreDto dto) {
        try {
            GenreModel entity = genreRepository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = genreRepository.save(entity);
            return new GenreDto(entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            genreRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Id not found " + id);
        } catch(EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("Id not found " + id, 0);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseExeption("Integrity violation");
        }
    }

}
