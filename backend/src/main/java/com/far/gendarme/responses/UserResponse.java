package com.far.gendarme.responses;

import com.far.gendarme.models.Diplome;
import com.far.gendarme.models.Fonction;
import com.far.gendarme.models.Formation;
import com.far.gendarme.models.Grade;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateNaissance;
    private int anciennete;
    private Grade grade;
    private Fonction fonction;
    private List<Diplome> diplomes = new ArrayList<>();
    private List<Formation> formations = new ArrayList<>();
}
