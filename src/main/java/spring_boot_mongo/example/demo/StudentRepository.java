package spring_boot_mongo.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<String> findStudentByEmail(String email);
    Student findByEmail(String email);
    Student findStudentById(String id);
}
