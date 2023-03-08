package com.example.Bookmyshowclone.Service;

import com.example.Bookmyshowclone.Models.Booking;
import com.example.Bookmyshowclone.Models.User;
import com.example.Bookmyshowclone.Repository.UserRepository;
import com.example.Bookmyshowclone.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String addUser(User user) {
        String password = user.getPassword();
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        userRepository.save(user);
        return "user added";
    }

    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public List<Booking> getUserBookings(String email) {
        return userRepository.findByEmail(email).getBookings();
    }

    public String userRegister(User user) {
        userRepository.save(user);
        return "sucess";
    }

    public String modifyUserDetails() {
        return "hi";
    }

    public User getUserDetails(HttpServletRequest httpServletRequest) throws Exception {
        String jsonToken = httpServletRequest.getHeader("Authorization");
        if (jsonToken == null)
            throw new Exception("User not logged in");
        jsonToken = jsonToken.substring(7);
        String userName = jwtUtil.extractUsername(jsonToken);
        System.out.println(userName);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println("hi");
            User user = userRepository.findByUserName(userName);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
            if (jwtUtil.validateToken(jsonToken, userDetails)) {
                return user;
            } else
                return null;
        }
        return null;
    }
}
