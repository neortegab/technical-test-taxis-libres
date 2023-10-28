package com.taxislibres.technicaltest.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.util.List;

@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Email
    private String email;

    private String password;

    @Min(0)
    private int age;

    @OneToMany
    private List<Bill> bills;

    @ManyToMany
    private List<UserRole> roles;
}
