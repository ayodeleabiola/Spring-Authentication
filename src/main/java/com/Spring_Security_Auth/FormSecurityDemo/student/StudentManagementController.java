package com.Spring_Security_Auth.FormSecurityDemo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    public  static final List<Student>student= Arrays.asList(
            new Student(1,"kunle"),
            new Student(2,"Hassan"),
            new Student(3,"mariam")
    );

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TRAIN')")
    public List<Student> getAllStudent(){
        System.out.println("getAllStudent");
        return student;
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void postNewStudent(@RequestBody Student student){
        System.out.println("postNewStudent");
    }

    @PreAuthorize("hasAnyAuthority('student:write')")
    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteStudent");
    }


    @PutMapping("{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student){
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s",studentId,student));
    }
}
