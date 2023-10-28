package com.taxislibres.technicaltest.Controllers;

import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Services.UserBillService;
import com.taxislibres.technicaltest.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final UserBillService userBillService;
    @Autowired
    public UserController(UserService userService, UserBillService userBillService){
        this.userService = userService;
        this.userBillService = userBillService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        var userFound = userService.getUserById(id);
        if(userFound.isEmpty()){
            return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<User>>(userFound, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        if(user.getEmail() == null) return new ResponseEntity<String>("Bad Request: Email not found", HttpStatus.BAD_REQUEST);
        else if(user.getAge() == 0) return new ResponseEntity<String>("Bad Request: Age not found", HttpStatus.BAD_REQUEST);
        else if(user.getName() == null) return new ResponseEntity<String>("Bad Request: Name not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        var user = userService.getUserById(id);
        if(user.isEmpty()) return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
        userService.deleteUser(id);
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }
}