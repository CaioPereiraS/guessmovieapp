package io.guessmovie.guessmovieapp.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;

@Table(name = "tb_movie")
public class MovieModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100, columnDefinition = "TEXT")
    private String title;

    @Column(name = "year", nullable = false, length = 4, columnDefinition = "TEXT")
    private String year;

    @Column(name = "director", nullable = false, length = 100, columnDefinition = "TEXT")
    private String director;

    @Column(name = "poster", nullable = false, length = 100, columnDefinition = "TEXT")
    private String poster;

    @Column(name = "rating_imdb", nullable = false, columnDefinition = "DOUBLE")
    private Double rating_imdb;

    @ManyToMany
    @JoinTable(name = "tb_movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    Set<GenreModel> genres = new HashSet<>();

    public MovieModel() {
    }

    public MovieModel(String title, String year, String director, String poster, Double rating_imdb) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.poster = poster;
        this.rating_imdb = rating_imdb;
    }

    // Getters and Setters
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

    public Set<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreModel> genres) {
        this.genres = genres;
    }
}

