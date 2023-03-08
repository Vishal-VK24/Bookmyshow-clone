package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.Movie;
import com.example.Bookmyshowclone.Models.Show;
import com.example.Bookmyshowclone.Repository.MovieRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public Movie getMovie(String movieName){
        return movieRepository.findBymovieName(movieName);
    }
    public String addMovie(Movie movie){
        movieRepository.save(movie);
        return "sucess";
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public List<Show> getMovieShows(String movie_name){
        Movie movie= movieRepository.findBymovieName(movie_name);
        if(movie==null)
            return null;
        return movie.getShows();

    }
}
