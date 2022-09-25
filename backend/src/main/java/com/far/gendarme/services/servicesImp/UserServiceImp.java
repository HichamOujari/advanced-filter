package com.far.gendarme.services.servicesImp;

import com.far.gendarme.Specifications.UserSpecification;
import com.far.gendarme.models.Grade;
import com.far.gendarme.models.User;
import com.far.gendarme.repositories.DiplomeRepository;
import com.far.gendarme.repositories.FormationRepository;
import com.far.gendarme.repositories.GradeRepository;
import com.far.gendarme.repositories.UserRepository;
import com.far.gendarme.requests.FilterRequest;
import com.far.gendarme.responses.UserResponse;
import com.far.gendarme.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    FormationRepository formationRepository;
    @Autowired
    DiplomeRepository diplomeRepository;


    @Override
    public List<User> getUserAndFilter(FilterRequest filterRequest) {
        Specification<User> spec = new UserSpecification(filterRequest);
        List<User> listUser = new ArrayList<>();
        userRepository.findAll(spec).forEach(ele -> {
            User user = new User();
            BeanUtils.copyProperties(ele,user);
            user.setGrade(new Grade(ele.getGrade().getId(),ele.getGrade().getName()));
            //user.getDiplomes().addAll(ele.getDiplomes().stream().toList());
            //user.getFormations().addAll(ele.getFormations().stream().toList());
            listUser.add(user);
        });

        return listUser;
    }

}
