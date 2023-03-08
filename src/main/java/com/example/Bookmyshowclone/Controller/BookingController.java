package com.example.Bookmyshowclone.Controller;

import com.example.Bookmyshowclone.Models.Booking;
import com.example.Bookmyshowclone.Models.Seat;
import com.example.Bookmyshowclone.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    public String addBooking(@RequestBody Booking booking){
        return bookingService.addBooking(booking);
    }
    @GetMapping("/bookings/{booking_id}")
    public Booking getBooking(@PathVariable int booking_id){
        return bookingService.getBooking(booking_id);
    }
    @GetMapping("/bookings/{booking_id}/seats")
    public List<Seat> getBookingSeats(@PathVariable int booking_id){
        return bookingService.getBookingSeats(booking_id);
    }
    @PostMapping("/bookings/{booking_id}/user/{email}")
    public String addBookingUser(@PathVariable int booking_id,@PathVariable String email){
        return bookingService.addBookingUser(booking_id,email);
    }
}
