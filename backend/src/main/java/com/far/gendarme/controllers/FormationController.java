package com.far.gendarme.controllers;

import com.far.gendarme.models.Formation;
import com.far.gendarme.models.User;
import com.far.gendarme.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("formations")
@CrossOrigin(origins = "*")
public class FormationController {

    @Autowired
    FormationRepository formationRepository;

    @GetMapping()
    public ResponseEntity<List<Formation>> getAllFormation(){
        return ResponseEntity.ok().body(this.formationRepository.findAll());
    }
}
