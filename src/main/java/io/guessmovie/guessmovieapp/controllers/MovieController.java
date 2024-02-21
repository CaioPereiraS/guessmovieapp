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

import io.guessmovie.guessmovieapp.dtos.MovieDto;
import io.guessmovie.guessmovieapp.services.MovieServices;

@RestController
@RequestMapping(value = "/Movies")
public class MovieController {
    
    @Autowired
    private MovieServices MovieServices;

    @GetMapping
    public ResponseEntity<Page<MovieDto>> findAll(Pageable pageRequest){
        Page<MovieDto> list = MovieServices.findAllPaged(pageRequest);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable Long id){
        MovieDto dto = MovieServices.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MovieDto> insert(@RequestBody MovieDto dto){
        dto = MovieServices.insert(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri()).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody MovieDto dto){
        dto = MovieServices.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MovieDto> delete(@PathVariable Long id){
        MovieServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
