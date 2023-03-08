package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.Seat;
import com.example.Bookmyshowclone.Repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    public String addSeat(Seat seat){
        seatRepository.save(seat);
        return "sucess";
    }
    public List<Seat> getAllSeats(){
        return seatRepository.findAll();
    }
    public Seat getSeat(int seat_id){
        return seatRepository.findById(seat_id).orElse(null);
    }
}
