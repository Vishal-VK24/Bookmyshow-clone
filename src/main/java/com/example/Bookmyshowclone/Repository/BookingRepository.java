package com.example.Bookmyshowclone.Repository;

import com.example.Bookmyshowclone.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findByShowId(int show_id);
}
