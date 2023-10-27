package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return repository.findById(id);
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public void deleteUser(User user){
         repository.delete(user);
    }
}
