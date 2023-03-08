package com.example.Bookmyshowclone.Controller;

import com.example.Bookmyshowclone.Models.Screen;
import com.example.Bookmyshowclone.Models.Seat;
import com.example.Bookmyshowclone.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping("/seats")
    public String addSeat(@RequestBody Seat seat){
        return seatService.addSeat(seat);
    }
    @GetMapping("/seats")
    public List<Seat> getAllSeats(){
        return seatService.getAllSeats();
    }
    @GetMapping("/seats/{seat_id}")
    public Seat getSeat(@PathVariable int seat_id){
        return seatService.getSeat(seat_id);
    }
}
