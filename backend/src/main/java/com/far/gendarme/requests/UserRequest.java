package com.far.gendarme.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private Date dateNaissance;
    private int anciennete;

    private List<Long> diplomes;
    private List<Long> formations;
    private Long grade;
    private Long fonction;

}
