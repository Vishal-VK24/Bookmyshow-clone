package com.example.Bookmyshowclone.Repository;

import com.example.Bookmyshowclone.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
    Theatre findBytheatreName(String theatreName);
}
