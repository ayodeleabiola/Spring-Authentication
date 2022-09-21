package com.Spring_Security_Auth.FormSecurityDemo.student;

import java.util.Arrays;
import java.util.List;

public class Student {
    public final Integer studentId;
    public final String studentName;
    public Student(Integer studentId,String studentName){
        this.studentId=studentId;
        this.studentName=studentName;
    }
    public Integer getStudentId(){
      return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
