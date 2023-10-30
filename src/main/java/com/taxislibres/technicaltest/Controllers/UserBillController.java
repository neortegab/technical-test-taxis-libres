package com.taxislibres.technicaltest.Controllers;

import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Services.UserBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/bills")
public class UserBillController {
    private final UserBillService service;

    @Autowired
    public UserBillController(UserBillService service){
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Bill>> getUserBills(@PathVariable Long userId){
        return new ResponseEntity<>(service.getBillsByUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getUserBillById(@PathVariable Long userId, @PathVariable Long billId){
        return new ResponseEntity<>(service.getUserBillById(userId, billId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Bill> createUserBill(@PathVariable Long userId, @RequestBody Bill bill){
        return new ResponseEntity<>(service.createUserBill(userId, bill), HttpStatus.OK);
    }
    @PutMapping("/{billId}")
    public ResponseEntity<Bill> updateUserBill(@PathVariable Long userId, @PathVariable Long billId, @RequestBody Bill bill){
        return new ResponseEntity<>(service.updateUserBill(userId, billId, bill), HttpStatus.OK);
    }
    @DeleteMapping("/{billId}")
    public ResponseEntity<Bill> removeUserBill(@PathVariable Long userId, @PathVariable Long billId){
        return new ResponseEntity<>(service.deleteUserBill(userId, billId), HttpStatus.OK);
    }
}
