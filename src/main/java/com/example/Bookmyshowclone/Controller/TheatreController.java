package com.example.Bookmyshowclone.Controller;

import com.example.Bookmyshowclone.Models.Screen;
import com.example.Bookmyshowclone.Models.Theatre;
import com.example.Bookmyshowclone.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @GetMapping("/theatres")
    public List<Theatre> getAllTheatres(){
        return theatreService.getAllTheatres();
    }
    @PostMapping("/theatres")
    public String addTheatre(@RequestBody Theatre theatre){
        return theatreService.addTheatre(theatre);
    }
    @GetMapping("/theatres/{theatre_name}")
    public Theatre getTheatre(@PathVariable String theatre_name){
        return theatreService.getTheatre(theatre_name);
    }
    @GetMapping("/theatres/{theatre_name}/screens")
    public List<Screen> getTheatreScreens(@PathVariable String theatre_name){
        return theatreService.getTheatreScreens(theatre_name);
    }
    @PostMapping("/theatres/{theatre_name}/{screen_name}")
    public String addTheatreScreens(@PathVariable String theatre_name, @PathVariable String screen_name){
        return theatreService.addTheatreScreens(theatre_name,screen_name);
    }
    @GetMapping("/theatres/nearby")
    public List<Theatre> getTheatresNearby(@RequestParam double latitude,@RequestParam double longitude){
        return theatreService.getTheatresNearby(latitude,longitude);
    }
}
