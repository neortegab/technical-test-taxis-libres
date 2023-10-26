package com.taxislibres.technicaltest.Repositories;

import com.taxislibres.technicaltest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
