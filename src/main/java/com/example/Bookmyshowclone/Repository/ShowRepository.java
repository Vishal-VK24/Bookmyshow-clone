package com.example.Bookmyshowclone.Repository;

import com.example.Bookmyshowclone.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {
}
