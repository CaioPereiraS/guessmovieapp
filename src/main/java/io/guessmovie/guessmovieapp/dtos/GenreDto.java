package io.guessmovie.guessmovieapp.dtos;

import java.io.Serializable;

import io.guessmovie.guessmovieapp.models.GenreModel;

public class GenreDto implements Serializable {
 
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public GenreDto() {
    }

    public GenreDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public GenreDto(GenreModel genreModel) {
        this.id = genreModel.getId();
        this.name = genreModel.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
