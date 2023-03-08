package com.example.Bookmyshowclone.Controller;

import com.example.Bookmyshowclone.Models.Movie;
import com.example.Bookmyshowclone.Models.Screen;
import com.example.Bookmyshowclone.Models.Seat;
import com.example.Bookmyshowclone.Service.MovieService;
import com.example.Bookmyshowclone.Service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @GetMapping("/screens/{screen_name}")
    public Screen getScreen(@PathVariable String screen_name){
        return screenService.getScreen(screen_name);
    }
    @PostMapping("/screens")
    public String addScreen(@RequestBody Screen screen){
        return screenService.addScreen(screen);
    }
    @GetMapping("/screens")
    public List<Screen> getAllScreens(){
        return screenService.getAllScreens();
    }
    @PostMapping("/screens/{screen_name}/seats/{seat_id}")
    public String addScreenSeat(@PathVariable String screen_name,@PathVariable int seat_id){
        return screenService.addScreenSeat(screen_name,seat_id);
    }
    @GetMapping("/screens/{screen_name}/seats")
    public List<Seat> getScreenSeats(@PathVariable String screen_name){
        return screenService.getScreenSeats(screen_name);
    }
}
