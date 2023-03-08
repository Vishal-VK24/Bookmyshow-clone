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
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String screenName;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="theatre_id")
    private Theatre theatre;
    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Show> shows;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Seat> seats;
}
