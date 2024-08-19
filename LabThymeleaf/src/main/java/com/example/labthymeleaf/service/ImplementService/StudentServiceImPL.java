package com.example.labthymeleaf.service.ImplementService;

import com.example.labthymeleaf.dao.StudentDAO;
import com.example.labthymeleaf.model.Student;
import com.example.labthymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImPL implements StudentService {
    @Autowired
    private StudentDAO studentDAO;
    @Override
    public Student findStudentById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentDAO.getAll();
    }

    @Override
    public List<Student> findStudentByName(String name) {
        return studentDAO.findByName(name);
    }

    @Override
    public List<Student> findStudentByYear(int fromYear, int toYear) {
        return studentDAO.findByYear(fromYear, toYear);
    }
}
