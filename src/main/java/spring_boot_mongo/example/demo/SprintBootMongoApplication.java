package spring_boot_mongo.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import javax.management.Query;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class SprintBootMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintBootMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address("England", "London", "NE9");
			String email = "meow@gmail.com";
			Student student = new Student("Abir", "Sobaha",email, Gender.Male, address, List.of("Computer Science", "Security"), BigDecimal.TEN, LocalDateTime.now());


//			usingMongoTemplateAndQuery(studentRepository, mongoTemplate, email, student);
			studentRepository.findStudentByEmail(email)
					.ifPresentOrElse(s-> {
						System.out.println(s + " already exists");
					}, ()->{
						System.out.println("Inserting new student " + student);
						studentRepository.insert(student);
					}
			);
		};

	}

	private static void usingMongoTemplateAndQuery(StudentRepository studentRepository, MongoTemplate mongoTemplate, String email, Student student) {
		org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query();
		query.addCriteria(Criteria.where("email").is(email));
		List<Student> students = mongoTemplate.find(query, Student.class);

		if(students.size() > 1){
			throw new IllegalStateException("More than one student found with identical email " + email);
		}
		if(students.isEmpty()){
			System.out.println("Inserting new student" + student);
			studentRepository.insert(student);
		} else{
			System.out.println("Student already exist with email " + email);
		}
	}

}
