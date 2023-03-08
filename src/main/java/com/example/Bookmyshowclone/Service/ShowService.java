package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.*;
import com.example.Bookmyshowclone.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private TheatreService theatreService;
    public String addShow(Show show){
        Screen screen=screenRepository.findByscreenName(show.getScreenName());
        Movie movie=movieRepository.findBymovieName(show.getMovieName());
        if(screen==null||movie==null)
            return "no such screen/movie";
        show.setScreen(screen);
        show.setMovie(movie);
        showRepository.save(show);
        return "success";
    }
    public List<Show> getShows(){
        return showRepository.findAll();
    }
    public List<Seat> getShowSeats(int show_id){
        Show show = showRepository.findById(show_id).orElse(null);
        if(show==null)
            return null;
        Screen screen=show.getScreen();
        List<Seat> seats = screen.getSeats();
        System.out.println("hi");
        List<Booking> booking= bookingRepository.findByShowId(show_id);
        for(Booking i:booking){
            for(Seat j: i.getSeats()){
                seats.remove(j);
            }
        }
        return seats;
    }
    public List<Show> getShowsPref(double latitude,double longitude,String language){
        if(language==null){
            if(latitude!=0 && longitude!=0){
                List<Theatre> theatres= theatreService.getTheatresNearby(latitude,longitude);
                List<Show> shows = getShows();
                List<Show> temp = getShows();
                for(Show i:temp){
                    if(!theatres.contains(i.getScreen().getTheatre()))
                        shows.remove(i);
                }
                return shows;
            }
            return getShows();
        }
        else{
            if(latitude!=0 && longitude!=0){
                List<Theatre> theatres= theatreService.getTheatresNearby(latitude,longitude);
                List<Show> shows = getShows();
                List<Show> temp = getShows();
                for(Show i:temp){
                    if(!theatres.contains(i.getScreen().getTheatre())|| !i.getMovie().getLanguage().equals(language))
                        shows.remove(i);
                }
                return shows;
            }
            else{
                List<Show> shows = getShows();
                List<Show> temp = getShows();
                for(Show i:temp){
                    if(!i.getMovie().getLanguage().equals(language))
                        shows.remove(i);
                }
                return shows;
            }
        }
    }
    public List<Show> getTheatreShows(String theatre_name){
        List<Show> shows = getShows();
        List<Show> temp = getShows();
        for(Show i:temp){
            if(!i.getScreen().getTheatre().getTheatreName().equals(theatre_name))
                shows.remove(i);
        }
        return shows;
    }

}
