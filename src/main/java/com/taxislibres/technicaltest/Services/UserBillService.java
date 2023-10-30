package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Exceptions.NotFoundException;
import com.taxislibres.technicaltest.Models.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBillService {
    private final UserService userService;
    private final BillService billService;

    @Autowired
    public UserBillService(BillService billService, UserService userService){
        this.billService = billService;
        this.userService = userService;
    }
    public List<Bill> getBillsByUserId(Long userId){
        var user = userService.getUserById(userId);
        return billService.getAllByUserId(user.getId());
    }

    public Bill getUserBillById(Long userId, Long billId){
        var user = userService.getUserById(userId);
        for(var userBill : user.getBills()){
            if(userBill.getId().equals(billId)) return userBill;
        }
        throw new NotFoundException("User", userId, "Bill", billId);
    }

    public Bill createUserBill(Long userId, Bill bill){
        var user = userService.getUserById(userId);
        bill.setUser(user);
        return billService.createBill(bill);
    }

    public Bill updateUserBill(Long userId, Long billId, Bill newBill){
        var user = userService.getUserById(userId);
        newBill.setUser(user);
        return billService.updateBill(newBill, billId);
    }

    public Bill deleteUserBill(Long userId, Long billId){
        var bill = billService.getBillById(billId);
        var userBills = getBillsByUserId(userId);
        var found = false;
        for(var userBill: userBills){
            if(userBill.getId().equals(bill.getId())){
                found = true;
                break;
            }
        }
        if(!found) throw new NotFoundException("User", userId, "bill", billId);
        return billService.deleteBill(billId);
    }
}
