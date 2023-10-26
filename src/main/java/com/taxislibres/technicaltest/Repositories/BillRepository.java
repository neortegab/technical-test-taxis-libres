package com.taxislibres.technicaltest.Repositories;

import com.taxislibres.technicaltest.Models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
}
