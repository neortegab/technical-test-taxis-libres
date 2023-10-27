package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }
    public List<Bill> getAllBills(){
        return repository.findAll();
    }
    public List<Bill> getAllBillsByUser(Long userId){
        return repository.findByUserId(userId);
    }
    public Optional<Bill> getBillById(Long id){
        return repository.findById(id);
    }

    public Bill createBill(Bill bill){
        return repository.save(bill);
    }

    public void deleteBill(Bill bill){
        repository.delete(bill);
    }
}