package com.example.Bookmyshowclone.Repository;

import com.example.Bookmyshowclone.Models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,Integer> {
    Screen findByscreenName(String screenName);
}
