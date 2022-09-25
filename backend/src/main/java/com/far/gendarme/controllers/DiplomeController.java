package com.far.gendarme.controllers;

import com.far.gendarme.models.Diplome;
import com.far.gendarme.models.Grade;
import com.far.gendarme.repositories.DiplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("diplomes")
@CrossOrigin(origins = "*")
public class DiplomeController {
    @Autowired
    DiplomeRepository diplomeRepository;

    @GetMapping()
    public ResponseEntity<List<Diplome>> getAllDiplome(){
        return ResponseEntity.ok().body(this.diplomeRepository.findAll());
    }
}
