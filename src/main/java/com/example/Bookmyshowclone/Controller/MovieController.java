package com.example.Bookmyshowclone.Controller;

import com.example.Bookmyshowclone.Models.Movie;
import com.example.Bookmyshowclone.Models.Show;
import com.example.Bookmyshowclone.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/{movie_name}")
    public Movie getMovie(@PathVariable String movie_name){
        return movieService.getMovie(movie_name);
    }
    @PostMapping("/movies")
    public String addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }
    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }
    @GetMapping("/movies/{movie_name}/shows")
    public List<Show> getMovieShows(@PathVariable String movie_name){
        return movieService.getMovieShows(movie_name);
    }
}
