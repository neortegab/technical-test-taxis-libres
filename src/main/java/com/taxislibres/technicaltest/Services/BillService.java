package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Exceptions.NotFoundException;
import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final BillRepository repository;
    @Autowired
    public BillService(BillRepository repository) {
        this.repository = repository;
    }
    public List<Bill> getAllBills(){
        return repository.findAll();
    }

    public List<Bill> getAllByUserId(Long userId) { return repository.findByUserId(userId); }
    public Bill getBillById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Bill", id));
    }
    public Bill createBill(Bill bill){
        return repository.save(bill);
    }
    public Bill updateBill(Bill newBill, Long billId){
        var bill = repository.findById(billId).orElseThrow(() -> new NotFoundException("Bill", billId));
        if(newBill.getDescription() == null) newBill.setDescription(bill.getDescription());
        if(newBill.getTotalAmount() == 0) newBill.setTotalAmount(bill.getTotalAmount());
        newBill.setId(bill.getId());
        newBill.setUser(bill.getUser());
        return repository.save(newBill);
    }
    public Bill deleteBill(Long billId){
        var bill = repository.findById(billId).orElseThrow(() -> new NotFoundException("bill", billId));
        repository.deleteById(billId);
        return bill;
    }
}