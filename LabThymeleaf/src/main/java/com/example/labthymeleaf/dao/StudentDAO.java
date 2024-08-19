package com.example.labthymeleaf.dao;

import com.example.labthymeleaf.model.Student;

import java.util.List;

public interface StudentDAO {
    Student findById(int id);

    List<Student> getAll();
    List<Student> findByName(String name);
    List<Student> findByYear(int fromYear,int toYear);
}
