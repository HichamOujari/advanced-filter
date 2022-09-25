package com.far.gendarme.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;
    private Date dateNaissance;
    private int anciennete;

    public User(String firstName,String lastName,String email, String password, Date dateNaissance,int anciennete) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setDateNaissance(dateNaissance);
        this.setAnciennete(anciennete);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_diplome",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "diplome_id")}
    )
    List<Diplome> diplomes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_formation",
            joinColumns ={ @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "formation_id")}
    )
    List<Formation> formations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="grade_id")
    Grade grade;
}
