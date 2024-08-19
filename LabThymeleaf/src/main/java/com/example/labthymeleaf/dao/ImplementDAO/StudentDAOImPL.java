package com.example.labthymeleaf.dao.ImplementDAO;

import com.example.labthymeleaf.dao.StudentDAO;
import com.example.labthymeleaf.database.StudentDB;
import com.example.labthymeleaf.model.Student;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentDAOImPL implements StudentDAO {
    @Override
    public Student findById(int id) {
        for (Student student : StudentDB.students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> getAll() {
        return StudentDB.students;
    }

    @Override
    public List<Student> findByName(String name) {

        return StudentDB.students.stream()
            .filter(student -> student.getName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }

    @Override
    public List<Student> findByYear(int fromYear, int toYear) {
        return StudentDB.students.stream()
            .filter(student -> student.getYear() >= fromYear && student.getYear() <= toYear)
            .collect(Collectors.toList());
    }
}
