package com.example.labthymeleaf.untils;

import com.example.labthymeleaf.model.Student;

import java.util.List;

public interface IFileReader {
    List<Student> readStudentData(String path);
}
