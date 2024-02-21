package io.guessmovie.guessmovieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.guessmovie.guessmovieapp.dtos.GenreDto;
import io.guessmovie.guessmovieapp.services.GenreServices;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {
    
    @Autowired
    private GenreServices genreServices;

    @GetMapping
    public ResponseEntity<Page<GenreDto>> findAll(Pageable pageRequest){
        Page<GenreDto> list = genreServices.findAllPaged(pageRequest);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GenreDto> findById(@PathVariable Long id){
        GenreDto dto = genreServices.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<GenreDto> insert(@RequestBody GenreDto dto){
        dto = genreServices.insert(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<GenreDto> update(@PathVariable Long id, @RequestBody GenreDto dto){
        dto = genreServices.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<GenreDto> delete(@PathVariable Long id){
        genreServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
