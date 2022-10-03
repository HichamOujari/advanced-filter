package com.far.gendarme.controllers;

import com.far.gendarme.models.*;
import com.far.gendarme.repositories.*;
import com.far.gendarme.requests.FilterRequest;
import com.far.gendarme.requests.GradeRequest;
import com.far.gendarme.requests.UserRequest;
import com.far.gendarme.responses.UserResponse;
import com.far.gendarme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiplomeRepository diplomeRepository;

    @Autowired
    FonctionRepository fonctionRepository;
    @Autowired
    FormationRepository formationRepository;

    @PostMapping("/filter")
    public ResponseEntity<List<User>> getUsersByFilter(@RequestBody FilterRequest filterRequest){
        return ResponseEntity.ok().body(userService.getUserAndFilter(filterRequest));
    }

    @PostMapping()
    public ResponseEntity<User> createNewGrade(@RequestBody UserRequest userRequest){
        User user = new User(userRequest.getFirstName(),userRequest.getLastName(),userRequest.getDateNaissance(),userRequest.getAnciennete());
        Optional<Grade> grade = this.gradeRepository.findById(userRequest.getGrade());
        if(grade.isPresent()) user.setGrade(grade.get());

        Optional<Fonction> fonction = this.fonctionRepository.findById(userRequest.getFonction());
        if(fonction.isPresent()) user.setFonction(fonction.get());

        List<Diplome> diplomes = this.diplomeRepository.findAllById(userRequest.getDiplomes());
        user.getDiplomes().addAll(diplomes);

        List<Formation> formations = this.formationRepository.findAllById(userRequest.getFormations());
        user.getFormations().addAll(formations);

        return ResponseEntity.ok().body(this.userRepository.save(user));
    }
}
