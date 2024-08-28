package spring_boot_mongo.example.demo;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student findStudentByEmail(String email){
        return studentRepository.findByEmail(email);
    }
    public Student findStudentById(String id){
        return studentRepository.findStudentById(id);
    }
}
