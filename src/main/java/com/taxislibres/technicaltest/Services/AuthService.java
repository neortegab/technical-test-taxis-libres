package com.taxislibres.technicaltest.Services;

import com.taxislibres.technicaltest.Exceptions.AlreadyExistsException;
import com.taxislibres.technicaltest.Models.User;
import com.taxislibres.technicaltest.Models.UserRole;
import com.taxislibres.technicaltest.Repositories.RoleRepository;
import com.taxislibres.technicaltest.Repositories.UserRepository;
import com.taxislibres.technicaltest.Security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthService(
            AuthenticationManager authManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtGenerator jwtGenerator) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    public AuthResponse loginUser(User user){
        var authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthResponse(jwtGenerator.generateToken(authentication), "Bearer ");
    }

    public User registerUser(User user){
        if(userRepository.existsByEmail(user.getEmail())) throw new AlreadyExistsException(
                String.format("User with email %s already exists", user.getEmail())
        );
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        var role = roleRepository.findByRole("USER")
                .orElseGet(() -> roleRepository.save(new UserRole(1L, "USER")));
        user.setRoles(Collections.singletonList(role));
        return userRepository.save(user);
    }

    public record AuthResponse(String token, String tokenType){}
}
