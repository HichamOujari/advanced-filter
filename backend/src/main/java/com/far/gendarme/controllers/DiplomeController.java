package com.far.gendarme.controllers;

import com.far.gendarme.models.Diplome;
import com.far.gendarme.models.Grade;
import com.far.gendarme.repositories.DiplomeRepository;
import com.far.gendarme.requests.DiplomeRequest;
import com.far.gendarme.requests.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping()
    public ResponseEntity<Diplome> createNewDiplome(@RequestBody DiplomeRequest diplomeRequest){
        Diplome diplome = new Diplome();
        diplome.setName(diplomeRequest.getName());
    }
}
