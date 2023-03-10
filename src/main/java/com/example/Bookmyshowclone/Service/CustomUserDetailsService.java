package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.CustomUserDetails;
import com.example.Bookmyshowclone.Models.User;
import com.example.Bookmyshowclone.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        CustomUserDetails userDetails=null;
        if(user!=null){
            userDetails=new CustomUserDetails();
            userDetails.setUser(user);
        }
        else{
            throw new UsernameNotFoundException("User not exist with given username");
        }
        return userDetails;
    }
}
