package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Exceptions.NotFoundException;
import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Bill> getBillById(Long id){
        var bill = repository.findById(id);
        if(bill.isEmpty()) throw new NotFoundException("Bill", id);
        return bill;
    }
    public Bill createBill(Bill bill){
        return repository.save(bill);
    }
    public Bill updateBill(Bill newBill, Long billId){
        var bill = repository.findById(billId);
        if(bill.isEmpty()) throw new NotFoundException("Bill", billId);
        if(newBill.getDescription() == null) newBill.setDescription(bill.get().getDescription());
        if(newBill.getTotalAmount() == 0) newBill.setTotalAmount(bill.get().getTotalAmount());
        newBill.setId(bill.get().getId());
        return repository.save(newBill);
    }
    public Bill deleteBill(Long billId){
        var bill = repository.findById(billId);
        if(bill.isEmpty()) throw new NotFoundException("bill", billId);
        repository.delete(bill.get());
        return bill.get();
    }
}