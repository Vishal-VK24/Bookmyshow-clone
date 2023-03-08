package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.Screen;
import com.example.Bookmyshowclone.Models.Theatre;
import com.example.Bookmyshowclone.Repository.ScreenRepository;
import com.example.Bookmyshowclone.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private LocationService locationService;
    public String addTheatre(Theatre theatre){
        theatreRepository.save(theatre);
        return "sucess";
    }
    public Theatre getTheatre(String theatre_name){
        return theatreRepository.findBytheatreName(theatre_name);
    }
    public List<Theatre> getAllTheatres(){
        return theatreRepository.findAll();
    }
    public String addTheatreScreens(String theatre_name, String screen_name){
        Theatre theatre= theatreRepository.findBytheatreName(theatre_name);
        Screen screen = screenRepository.findByscreenName(screen_name);
        if(theatre==null|| screen==null){
            return "no such theatre/screen";
        }
        List<Screen> temp= theatre.getScreens();
        temp.add(screen);
        theatre.setScreens(temp);
        theatreRepository.save(theatre);
        screen.setTheatre(theatre);
        screenRepository.save(screen);
        return "success";
    }

    public List<Theatre> getTheatresNearby(double latitude,double longitude){
        List<Theatre> theatres = theatreRepository.findAll();
        List<Theatre> result=theatreRepository.findAll();
        for(Theatre i : theatres){
            if(locationService.distance(latitude,longitude,i.getLatitude(),i.getLongitude(),'K')>50.0){
                result.remove(i);
            }
        }
        return result;
    }
    public List<Screen> getTheatreScreens(String theatre_name){
        Theatre theatre = theatreRepository.findBytheatreName(theatre_name);
        if(theatre==null)
            return null;
        return theatre.getScreens();
    }
}
