package com.example.labthymeleaf.service;

import com.example.labthymeleaf.model.Student;

import java.util.List;

public interface StudentService {
    Student findStudentById(int id);

    List<Student> getAllStudent();
    List<Student> findStudentByName(String name);
    List<Student> findStudentByYear(int fromYear,int toYear);
}
