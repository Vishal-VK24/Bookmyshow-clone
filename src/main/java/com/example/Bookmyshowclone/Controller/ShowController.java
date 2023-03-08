package com.example.Bookmyshowclone.Controller;

import com.example.Bookmyshowclone.Models.Booking;
import com.example.Bookmyshowclone.Models.Seat;
import com.example.Bookmyshowclone.Models.Show;
import com.example.Bookmyshowclone.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/shows")
    public String addShow(@RequestBody Show show){
        return showService.addShow(show);
    }
    @GetMapping("/shows")
    public List<Show> getShows(){
        return showService.getShows();
    }
    @GetMapping("/shows/{show_id}/seats")
    public List<Seat> getShowSeats(@PathVariable int show_id){
        return showService.getShowSeats(show_id);
    }

    @GetMapping("/shows/pref")
    public List<Show> getShowsPref(@RequestParam(required = false) Double latitude,@RequestParam(required = false) Double longitude,@RequestParam(required = false) String language){
        System.out.println(language);
        if(latitude!=null&&longitude!=null)
            return showService.getShowsPref(latitude,longitude,language);
        return showService.getShowsPref(0,0,language);
    }
    @GetMapping("/theatres/shows")
    public List<Show> getTheatreShows(@RequestParam String theatre_name){
        return showService.getTheatreShows(theatre_name);
    }
}
