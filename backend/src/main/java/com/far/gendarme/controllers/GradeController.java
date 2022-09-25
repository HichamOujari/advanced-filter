package com.far.gendarme.controllers;

import com.far.gendarme.models.Formation;
import com.far.gendarme.models.Grade;
import com.far.gendarme.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("grades")
@CrossOrigin(origins = "*")
public class GradeController {

    @Autowired
    GradeRepository gradeRepository;

    @GetMapping()
    public ResponseEntity<List<Grade>> getAllGrades(){
        return ResponseEntity.ok().body(this.gradeRepository.findAll());
    }
}
