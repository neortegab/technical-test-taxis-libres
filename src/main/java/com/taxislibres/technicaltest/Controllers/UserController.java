package com.taxislibres.technicaltest.Controllers;

import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Services.UserBillService;
import com.taxislibres.technicaltest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id){
        return new ResponseEntity<Optional<User>>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/{userId}/bills")
    public ResponseEntity<?> getUserBills(@PathVariable Long userId){
        return new ResponseEntity<List<Bill>>(userBillService.getBillsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}