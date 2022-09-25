package com.far.gendarme.controllers;

import com.far.gendarme.models.User;
import com.far.gendarme.repositories.DiplomeRepository;
import com.far.gendarme.repositories.FormationRepository;
import com.far.gendarme.repositories.GradeRepository;
import com.far.gendarme.requests.FilterRequest;
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

@Controller
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/filter")
    public ResponseEntity<List<User>> getUsersByFilter(@RequestBody FilterRequest filterRequest){
        return ResponseEntity.ok().body(userService.getUserAndFilter(filterRequest));
    }
}
