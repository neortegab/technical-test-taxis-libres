package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBillService {
    private final BillRepository billRepository;

    @Autowired
    public UserBillService(BillRepository billRepository){
        this.billRepository = billRepository;
    }
    public List<Bill> getBillsByUserId(Long userId){
        return billRepository.findByUserId(userId);
    }
}
