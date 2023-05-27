package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService ;
    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("new movie added successfully", HttpStatus.OK);
    }
    @PostMapping("/addDirector")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("new director added successfully", HttpStatus.OK);
    }
    @PutMapping("/addMovieDirectorPair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName){
        try {
            movieService.addMovieDirectorPair(movieName, directorName);
            return new ResponseEntity<>("success message wrapped in a ResponseEntity object", HttpStatus.OK);
        }catch (DirectorInvalidException ex){
            return new ResponseEntity<>("invalid director name", HttpStatus.BAD_REQUEST);
        }catch (MovieInvalidException ex){
            return new ResponseEntity<>("invalid movie name", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getMovieByName")
    public ResponseEntity<Movie> getMovieByName(@RequestParam String name) {
        try {
            Movie movie = movieService.getMovieByName(name);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        }catch (MovieInvalidException ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getDirectorByName")
    public ResponseEntity<Director> getDirectorByName(@RequestParam String directorName){
        try {
            Director director = movieService.getDirectorByName(directorName);
            return new ResponseEntity<>(director, HttpStatus.OK);
        }catch (DirectorInvalidException ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getMoviesByDirectorName")
    public ResponseEntity<List<String>>  getMoviesByDirectorName(@RequestParam String directorName){
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(directorName), HttpStatus.OK);
    }
    @GetMapping("/findAllMovies")
    public ResponseEntity<List<String>> findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
    }
    @DeleteMapping("/deleteDirectorByName")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("director deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deleteAllDirectors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("all the directors are deleted successfully", HttpStatus.OK);
    }
}
