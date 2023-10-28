package com.taxislibres.technicaltest.Controllers;

import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/bills")
public class BillController {
    private final BillService billService;
    @Autowired
    public BillController(BillService billService){ this.billService = billService; }
    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills(){
        return new ResponseEntity<List<Bill>>(billService.getAllBills(), HttpStatus.OK);
    }
    @GetMapping("/{billId}")
    public ResponseEntity<Optional<Bill>> getBillById(@PathVariable Long billId){
        return new ResponseEntity<Optional<Bill>>(billService.getBillById(billId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill){
        return new ResponseEntity<Bill>(billService.createBill(bill), HttpStatus.CREATED);
    }
    @PutMapping("/{billId}")
    public ResponseEntity<Bill> updateBill(@RequestBody Bill bill, @PathVariable Long billId){
        return new ResponseEntity<Bill>(billService.updateBill(bill, billId), HttpStatus.OK);
    }
    public ResponseEntity<Bill> deleteBill(@PathVariable Long billId){
        return new ResponseEntity<Bill>(billService.deleteBill(billId), HttpStatus.OK);
    }
}