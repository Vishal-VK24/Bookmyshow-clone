package com.example.Bookmyshowclone.Controller;

import com.example.Bookmyshowclone.Filter.JwtFilter;
import com.example.Bookmyshowclone.Models.Booking;
import com.example.Bookmyshowclone.Models.JWT.AuthRequest;
import com.example.Bookmyshowclone.Models.User;
import com.example.Bookmyshowclone.Service.UserService;
import com.example.Bookmyshowclone.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/users")
    public String addUser(@Valid @RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/users/{email}")
    public User getUser(@PathVariable String email){
        return userService.getUser(email);
    }
    @GetMapping("/users/showdetails")
    public User getUserDetails(HttpServletRequest httpServletRequest) throws Exception{
        return userService.getUserDetails(httpServletRequest);
    }
    @GetMapping("/users/bookings")
    public List<Booking> getUserBookings(@RequestParam String email){
        return userService.getUserBookings(email);
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegister(@RequestBody User user){
        return userService.addUser(user);
    }
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())

            );
        } catch (Exception exception){
            throw new Exception("Invalid user credentials");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
    @PutMapping("/users")
    public String modifyUserDetails(){
        return userService.modifyUserDetails();
    }
}
