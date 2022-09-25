package com.far.gendarme.models;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="diplomes")
public class Diplome {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true, nullable = false)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "diplomes")
    @JsonBackReference
    Set<User> users;
}