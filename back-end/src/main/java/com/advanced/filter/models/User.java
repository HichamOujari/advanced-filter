package com.advanced.filter.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique=true)
    private String email;
    private String password;
    private Date dateNaissance;
    private Date engagement;

    @OneToOne
    private Formation formation;

    @OneToOne
    private Grade grade;

}
