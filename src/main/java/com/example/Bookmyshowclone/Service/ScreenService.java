package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.Screen;
import com.example.Bookmyshowclone.Models.Seat;
import com.example.Bookmyshowclone.Repository.ScreenRepository;
import com.example.Bookmyshowclone.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService{
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private SeatRepository seatRepository;
    public Screen getScreen(String screen_name){
        return screenRepository.findByscreenName(screen_name);
    }
    public String addScreen(Screen screen){
        screenRepository.save(screen);
        return "sucess";
    }
    public List<Screen> getAllScreens(){
        return screenRepository.findAll();
    }
    public String addScreenSeat(String screen_name,int seat_id){
        Screen screen = screenRepository.findByscreenName(screen_name);
        Seat seat = seatRepository.findById(seat_id).orElse(null);
        if(screen==null)
            return "no such screen";
        if(seat==null)
            return "no such seat";
        seat.setScreen(screen);
        seatRepository.save(seat);
        return "success";

    }
    public List<Seat> getScreenSeats(String screen_name){
        Screen screen=screenRepository.findByscreenName(screen_name);
        if(screen==null)
            return null;
        return screen.getSeats();
    }
}
