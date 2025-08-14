package com.Ayush.SpringSecurityExample.Controller;

import com.Ayush.SpringSecurityExample.Model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>(List.of(
            new Student(1 , "ayush" , 80),
            new Student(2 , "nisha" , 90),
            new Student(3 , "rajesh" , 100)
    ));

    @GetMapping("/students")
    public List<Student> getStudent(){
        return students;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
         students.add(student);
         return student;
    }

    @GetMapping("/csrf-token") // generating the csrf token
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
