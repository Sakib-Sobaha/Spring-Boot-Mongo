package spring_boot_mongo.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;



@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;


    @GetMapping
    public List<Student> fetchAllStudents(){
        return studentService.getAllStudents();

    }

    record NewStudentRequest(String firstName, String lastName, String email, Gender gender, Address address, List<String> favouriteCourses, BigDecimal totalSpentOInBooks, LocalDateTime createdAt){

    }

    @PostMapping
    public void addStudent(@RequestBody NewStudentRequest request){
        Student student = new Student();
        setStudentDetails(request, student);

    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") String studentId){
        studentRepository.deleteById(studentId);
    }

    @PutMapping("{studentId}")
    public void updateStudent(@RequestBody NewStudentRequest request, @PathVariable("studentId") String studentId){
        Student student = studentService.findStudentById(studentId);
        if(student == null){
            System.out.println("Student not found");
        }
        setStudentDetails(request, student);

        // Find the student with the email



    }

    public void setStudentDetails(@RequestBody NewStudentRequest request, Student student) {
        student.setFirstName(request.firstName);
        student.setLastName(request.lastName);
        student.setEmail(request.email);
        student.setGender(request.gender);
        student.setAddress(request.address);
        student.setFavouriteCourses(request.favouriteCourses);
        student.setTotalSpentOInBooks(request.totalSpentOInBooks);
        student.setCreatedAt(request.createdAt);
        studentRepository.save(student);
    }


}



