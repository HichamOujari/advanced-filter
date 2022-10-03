package com.far.gendarme.controllers;

import com.far.gendarme.models.Diplome;
import com.far.gendarme.models.Fonction;
import com.far.gendarme.models.Grade;
import com.far.gendarme.repositories.FonctionRepository;
import com.far.gendarme.repositories.GradeRepository;
import com.far.gendarme.requests.DiplomeRequest;
import com.far.gendarme.requests.FonctionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<Fonction> createNewFonction(@RequestBody FonctionRequest fonctionRequest){
        Fonction fonction = new Fonction();
        fonction.setName(fonctionRequest.getName());
        return ResponseEntity.ok().body(this.fonctionRepository.save(fonction));
    }
}