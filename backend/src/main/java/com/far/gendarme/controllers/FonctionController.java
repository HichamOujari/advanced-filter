package com.far.gendarme.controllers;

import com.far.gendarme.models.Fonction;
import com.far.gendarme.models.Grade;
import com.far.gendarme.repositories.FonctionRepository;
import com.far.gendarme.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("fonctions")
@CrossOrigin(origins = "*")
public class FonctionController {

    @Autowired
    FonctionRepository fonctionRepository;

    @GetMapping()
    public ResponseEntity<List<Fonction>> getAllFonction(){
        return ResponseEntity.ok().body(this.fonctionRepository.findAll());
    }
}