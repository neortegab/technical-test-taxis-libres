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
        var bill = repository.findById(id);
        if(bill.isEmpty()) throw new NotFoundException("Bill", id);
        return bill.get();
    }
    public Bill createBill(Bill bill){
        return repository.save(bill);
    }
    public Bill updateBill(Bill newBill, Long billId){
        var bill = repository.findById(billId);
        if(bill.isEmpty()) throw new NotFoundException("Bill", billId);
        var oldBill = bill.get();
        if(newBill.getDescription() == null) newBill.setDescription(oldBill.getDescription());
        if(newBill.getTotalAmount() == 0) newBill.setTotalAmount(oldBill.getTotalAmount());
        newBill.setId(oldBill.getId());
        newBill.setUser(oldBill.getUser());
        return repository.save(newBill);
    }
    public Bill deleteBill(Long billId){
        var bill = repository.findById(billId);
        if(bill.isEmpty()) throw new NotFoundException("bill", billId);
        repository.delete(bill.get());
        return bill.get();
    }
}