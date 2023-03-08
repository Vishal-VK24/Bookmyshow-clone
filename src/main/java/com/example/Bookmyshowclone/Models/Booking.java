package com.example.Bookmyshowclone.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="show_id")
    private Show show;
   @ManyToMany(cascade = CascadeType.MERGE)
   @JoinTable(name="booking_seat_pairs",joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns =@JoinColumn(name="seat_id"))
   private List<Seat> seats;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="user_id")
    private User user;

}
