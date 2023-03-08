package com.example.Bookmyshowclone.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seatNumber;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "seats")
    @JsonIgnore
    private List<Booking> bookings;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="screen_id")
    private Screen screen;
}
