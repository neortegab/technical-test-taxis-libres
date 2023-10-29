package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Exceptions.NotFoundException;
import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User getUserById(Long id){
        var user = repository.findById(id);
        if(user.isEmpty()) throw new NotFoundException("User", id);
        return user.get();
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public User updateUser(User newUser, Long userId) {
        var user = repository.findById(userId);
        if(user.isEmpty()) throw new NotFoundException("User", userId);
        if(newUser.getName() == null) newUser.setName(user.get().getName());
        if(newUser.getAge() == 0) newUser.setAge(user.get().getAge());
        if(newUser.getEmail() == null) newUser.setEmail(user.get().getEmail());
        newUser.setId(user.get().getId());
        return repository.save(newUser);
    }

    public void deleteUser(Long userId){
        var user = repository.findById(userId);
        if(user.isEmpty()) throw new NotFoundException("User", userId);
        repository.deleteById(userId);
    }
}
