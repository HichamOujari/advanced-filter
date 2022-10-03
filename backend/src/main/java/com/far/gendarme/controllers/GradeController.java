package com.far.gendarme.controllers;

import com.far.gendarme.models.Fonction;
import com.far.gendarme.models.Formation;
import com.far.gendarme.models.Grade;
import com.far.gendarme.repositories.GradeRepository;
import com.far.gendarme.requests.FonctionRequest;
import com.far.gendarme.requests.GradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ResponseEntity<Grade> createNewGrade(@RequestBody GradeRequest gradeRequest){
        Grade grade = new Grade();
        grade.setName(gradeRequest.getName());
        return ResponseEntity.ok().body(this.gradeRepository.save(grade));
    }
}
