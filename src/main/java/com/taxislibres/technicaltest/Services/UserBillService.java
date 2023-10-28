package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Exceptions.NotFoundException;
import com.taxislibres.technicaltest.Models.Bill;
import com.taxislibres.technicaltest.Repositories.BillRepository;
import com.taxislibres.technicaltest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBillService {
    private final UserRepository userRepository;
    private final BillRepository billRepository;

    @Autowired
    public UserBillService(BillRepository billRepository, UserRepository userRepository){
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }
    public List<Bill> getBillsByUserId(Long userId){
        var user = userRepository.findById(userId);
        if(user.isEmpty()) throw new NotFoundException("User", userId);
        return billRepository.findByUserId(userId);
    }
}
