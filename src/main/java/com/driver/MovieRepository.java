package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private Map<String, Movie> movieData = new HashMap<>();
    private Map<String, Director> directorData = new HashMap<>();
    private Map<String, ArrayList<String>> directermovieData = new HashMap<>();
    public void add(Movie movie) {
        movieData.put(movie.getName(), movie);
    }

    public void add(Director director) {
        directorData.put(director.getName(), director);
    }

    public void add(String movieName, String directorName) {
        ArrayList<String> al = directermovieData.getOrDefault(directorName, new ArrayList<>());
        al.add(movieName);
        directermovieData.put(directorName, al);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return directermovieData.getOrDefault(directorName, new ArrayList<>());
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieData.keySet());
    }

    public void deleteDirector(String directorName) {
        directorData.remove(directorName);
    }

    public void deleteAllDirectors() {
    }

    public Optional<Movie> getMovie(String movieName) {
        if(movieData.containsKey(movieName)){
            return Optional.of(movieData.get(movieName));
        }
        return Optional.empty();
    }

    public Optional<Director> getDirector(String directorName) {
        if(directorData.containsKey(directorName)){
            return Optional.of(directorData.get(directorName));
        }
        return Optional.empty();
    }

    public void deleteMovie(String movie) {
        movieData.remove(movie);
    }

    public List<String> getDirectors() {
        return new ArrayList<>(directorData.keySet());
    }
}
