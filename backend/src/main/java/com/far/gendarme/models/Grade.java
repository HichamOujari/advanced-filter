package com.far.gendarme.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="grades")
public class Grade {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true, nullable = false)
    private String name;

    @OneToMany(mappedBy="grade", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<User> users;

    public Grade(Long id,String grade){
        this.setId(id);
        this.setName(grade);
    }
}