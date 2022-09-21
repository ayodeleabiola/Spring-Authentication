package com.Spring_Security_Auth.FormSecurityDemo.controller;

import com.Spring_Security_Auth.FormSecurityDemo.student.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class Controller {
    private  static  final List<Student> student= Arrays.asList(
            new Student(1,"Abiola"),
            new Student(2,"Owolabi"),
            new Student(3,"Ayodele")
    );
    //hasRole('ROLE_'hasAnyRole('ROLE_')hasAuthority('permission')hasPermission('permission'))

    @GetMapping("{studentId}")

    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return student.stream()
                      .filter(student->studentId.equals(student.getStudentId()))
                      .findFirst()
                      .orElseThrow(()-> new IllegalStateException("student with"+studentId+"does not exist"));

    }
}
