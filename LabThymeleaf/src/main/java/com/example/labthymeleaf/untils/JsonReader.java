package com.example.labthymeleaf.untils;

import com.example.labthymeleaf.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonReader implements IFileReader {
    @Override
    public List<Student> readStudentData(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> students = new ArrayList<>();

        try {
            students = objectMapper.readValue(new File(path),
                new TypeReference<List<Student>>() {
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
