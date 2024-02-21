package io.guessmovie.guessmovieapp.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.guessmovie.guessmovieapp.models.MovieModel;

public class MovieDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String year;
    private String director;
    private String poster;
    private Double rating_imdb;

    private List<GenreDto> genres = new ArrayList<>();

    public MovieDto() {
    }

    public MovieDto(Long id, String title, String year, String director, String poster, Double rating_imdb, List<GenreDto> genres) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.poster = poster;
        this.rating_imdb = rating_imdb;
        this.genres = genres;
    }
      public MovieDto(MovieModel MovieModel) {
        this.id = MovieModel.getId();
        this.title = MovieModel.getTitle();
        this.year = MovieModel.getYear();
        this.director = MovieModel.getDirector();
        this.poster = MovieModel.getPoster();
        this.rating_imdb = MovieModel.getRating_imdb();
        this.genres = MovieModel.getGenres().stream().map(x -> new GenreDto(x)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Double getRating_imdb() {
        return rating_imdb;
    }

    public void setRating_imdb(Double rating_imdb) {
        this.rating_imdb = rating_imdb;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }
}

