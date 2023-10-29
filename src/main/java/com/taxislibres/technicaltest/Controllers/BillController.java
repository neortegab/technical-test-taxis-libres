package com.taxislibres.technicaltest.Controllers;

import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/bills")
public class BillController {
    private final BillService billService;
    @Autowired
    public BillController(BillService billService){ this.billService = billService; }
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills(){
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }
    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        return new ResponseEntity<>(billService.getBillById(billId), HttpStatus.OK);
    }
    @PutMapping("/{billId}")
    public ResponseEntity<Bill> updateBill(@RequestBody Bill bill, @PathVariable Long billId){
        return new ResponseEntity<>(billService.updateBill(bill, billId), HttpStatus.OK);
    }
    @DeleteMapping("/{billId}")
    public ResponseEntity<Bill> deleteBill(@PathVariable Long billId){
        return new ResponseEntity<>(billService.deleteBill(billId), HttpStatus.OK);
    }
}