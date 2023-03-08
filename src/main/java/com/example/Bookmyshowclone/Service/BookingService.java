package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.Booking;
import com.example.Bookmyshowclone.Models.Seat;
import com.example.Bookmyshowclone.Models.User;
import com.example.Bookmyshowclone.Repository.BookingRepository;
import com.example.Bookmyshowclone.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;

    public String addBooking(Booking booking){
        System.out.println(booking.getSeats().size());
        bookingRepository.save(booking);
        return "success";
    }
    public Booking getBooking(int booking_id){
        return bookingRepository.findById(booking_id).orElse(null);
    }
    public List<Seat> getBookingSeats(int booking_id){
        Booking booking = bookingRepository.findById(booking_id).orElse(null);
        if(booking==null)
            return null;
        return booking.getSeats();
    }
    public String addBookingUser(int booking_id,String email){
        Booking booking=bookingRepository.findById(booking_id).orElse(null);
        if(booking==null)
            return "no such booking";
        User user = userRepository.findByEmail(email);
        if(user==null)
            return "no such user";
        booking.setUser(user);
        bookingRepository.save(booking);
        return "sucess";
    }
}
