package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    @Autowired
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

    public User updateUser(User user, Long userId) {
        user.setId(userId);
        return repository.save(user);
    }

    public void deleteUser(Long userId){
         repository.deleteById(userId);
    }
}
