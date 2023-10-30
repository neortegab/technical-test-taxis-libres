package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Exceptions.NotFoundException;
import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Models.UserRole;
import com.taxislibres.technicaltest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User", id));
    }

    public User createUser(User user){
        return repository.save(user);
    }

    public User updateUser(User newUser, Long userId) {
        var user = repository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
        if(newUser.getName() == null) newUser.setName(user.getName());
        if(newUser.getAge() == 0) newUser.setAge(user.getAge());
        if(newUser.getEmail() == null) newUser.setEmail(user.getEmail());
        newUser.setId(user.getId());
        return repository.save(newUser);
    }

    public void deleteUser(Long userId){
        repository.findById(userId).orElseThrow(() -> new NotFoundException("User", userId));
        repository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("Username with email %s was not found", username)
                        ));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<UserRole> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
