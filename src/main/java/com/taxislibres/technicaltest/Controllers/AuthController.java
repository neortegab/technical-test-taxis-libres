package com.taxislibres.technicaltest.Controllers;

import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody Login login){
        var user = new User();
        user.setEmail(login.email());
        user.setPassword(login.password());
        return new ResponseEntity<>(authService.loginUser(user), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(authService.registerUser(user), HttpStatus.OK);
    }

    public record Login(String email, String password){}
}
