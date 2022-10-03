package com.far.gendarme;

import com.far.gendarme.models.*;
import com.far.gendarme.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class
GendarmeApplication {

	@Autowired
	UserRepository userRepository;
	@Autowired
	GradeRepository gradeRepository;
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	DiplomeRepository diplomeRepository;
	@Autowired
	FonctionRepository fonctionRepository;



	public static void main(String[] args) {
		SpringApplication.run(GendarmeApplication.class, args);
	}

}
