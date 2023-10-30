package com.taxislibres.technicaltest.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Min(0)
    private double totalAmount;
    private String description;
    @ManyToOne
    @NotNull
    private User user;
}