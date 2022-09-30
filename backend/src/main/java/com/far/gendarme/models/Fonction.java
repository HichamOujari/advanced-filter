package com.far.gendarme.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="fonctions")
public class Fonction {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true, nullable = false)
    private String name;

    @OneToMany(mappedBy="fonction", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> users;

    public Fonction(Long id,String grade){
        this.setId(id);
        this.setName(grade);
    }
}