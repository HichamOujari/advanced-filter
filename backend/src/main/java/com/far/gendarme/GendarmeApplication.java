package com.far.gendarme;

import com.far.gendarme.models.*;
import com.far.gendarme.repositories.DiplomeRepository;
import com.far.gendarme.repositories.FormationRepository;
import com.far.gendarme.repositories.GradeRepository;
import com.far.gendarme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class GendarmeApplication {

	@Autowired
	UserRepository userRepository;
	@Autowired
	GradeRepository gradeRepository;
	@Autowired
	FormationRepository formationRepository;
	@Autowired
	DiplomeRepository diplomeRepository;



	public static void main(String[] args) {
		SpringApplication.run(GendarmeApplication.class, args);
	}

	@Bean
	public CommandLineRunner console() {
		return args -> {
			System.out.println("-----------Execution du programme------------");
			try{
				String[] listFormations = {"Cours d’application","Cours de capitaine","Cours d’état major","Ecole de guerre"};
				List<Formation> listCreatedFormations= new ArrayList<>();

				String[] listDiplomes = {"Ingénieur Informatique","Ingénieur Mécanique","Ingénieur civile","Ingénieur électrique","Ingénieur réseau"};
				List<Diplome> listCreatedDiplomes= new ArrayList<>();

				Arrays.stream(listFormations).forEach(ele -> {
					Formation formation = new Formation();
					formation.setName(ele);
					listCreatedFormations.add(formationRepository.save(formation));
				});

				Arrays.stream(listDiplomes).forEach(ele -> {
					Diplome diplome = new Diplome();
					diplome.setName(ele);
					listCreatedDiplomes.add(diplomeRepository.save(diplome));
				});

				String[] listGrades = {"Sous Lieutenant","Lieutenant","Capitaine","Commandant","Lieutenant Colonel","Colonel","Colonel Major","Général de brigade","Général de division","Général de corps d’armée"};
				List<Grade> listCreatedGrades= new ArrayList<>();

				Arrays.stream(listGrades).forEach(ele -> {
					Grade grade = new Grade();
					grade.setName(ele);
					listCreatedGrades.add(gradeRepository.save(grade));
				});

				SimpleDateFormat dateFormat =new SimpleDateFormat("dd/MM/yyyy");
				List<User> listeUsers = Arrays.asList(
						new User("Hicham","Oujari","hichamouajri99@gmail.com","pass123",dateFormat.parse("02/09/1999"),1),
						new User("Ayoub","Hadji","ayoubhadji@gmail.com","pass123",dateFormat.parse("22/02/1979"),2),
						new User("Elferdi","Said","SaidElferdi@gmail.com","pass123",dateFormat.parse("12/04/1995"),3),
						new User("youssef","Kacimi","youssef@gmail.com","pass123",dateFormat.parse("13/12/1967"),4)
				);
				Random random = new Random();
				listeUsers.forEach(ele ->{
					ele.getDiplomes().add(listCreatedDiplomes.get(random.nextInt( 5)));
					ele.getFormations().add(listCreatedFormations.get(random.nextInt(4)));
					ele.setGrade(listCreatedGrades.get(random.nextInt(10)));
					this.userRepository.save(ele);
				});
			}catch(Exception err){
				System.out.println("----------- Date base already filled -----------");
			}
			System.out.println("-----------Fin Execution du programme------------");
		};
	}
}
