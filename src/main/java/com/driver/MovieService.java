package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MovieService {
    @Autowired
    private  MovieRepository movieRepository;
    public void addMovie(Movie movie) {
        movieRepository.add(movie);
    }

    public void addDirector(Director director) {
        movieRepository.add(director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) throws MovieInvalidException, DirectorInvalidException{
        Optional<Movie> movieOptional = movieRepository.getMovie(movieName);
        Optional<Director> directorOptional = movieRepository.getDirector(directorName);
        if(movieOptional.isEmpty()){
            throw new MovieInvalidException();
        }
        if(directorOptional.isEmpty()){
            throw new DirectorInvalidException();
        }
        Director directorObj = directorOptional.get();
        directorObj.setNumberOfMovies(directorObj.getNumberOfMovies() + 1);
        movieRepository.add(directorObj);
        movieRepository.add(movieName, directorName);
    }

    public Movie getMovieByName(String name)throws  MovieInvalidException{
        Optional<Movie> optionalMovie = movieRepository.getMovie(name);
        if(optionalMovie.isEmpty()){
            throw new MovieInvalidException();
        }
        return optionalMovie.get();
    }

    public Director getDirectorByName(String directorName)throws DirectorInvalidException{
        Optional<Director> optionalDirector = movieRepository.getDirector(directorName);
        if(optionalDirector.isEmpty()){
            throw new DirectorInvalidException();
        }
        return optionalDirector.get();
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String directorName) {
        List<String> movies = getMoviesByDirectorName(directorName);
        movieRepository.deleteDirector(directorName);
        for(String movie : movies){
            movieRepository.deleteMovie(movie);
        }
    }

    public void deleteAllDirectors() {
        List<String> directors = movieRepository.getDirectors();
        for(String director : directors){
            deleteDirectorByName(director);
        }
    }
}
