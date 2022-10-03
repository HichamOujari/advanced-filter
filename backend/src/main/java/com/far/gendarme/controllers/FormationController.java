package com.far.gendarme.controllers;

import com.far.gendarme.models.Formation;
import com.far.gendarme.models.Grade;
import com.far.gendarme.models.User;
import com.far.gendarme.repositories.FormationRepository;
import com.far.gendarme.requests.FormationRequest;
import com.far.gendarme.requests.GradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<Formation> createNewFormation(@RequestBody FormationRequest formationRequest){
        Formation formation = new Formation();
        formation.setName(formationRequest.getName());
        return ResponseEntity.ok().body(this.formationRepository.save(formation));
    }
}
