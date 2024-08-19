package com.example.labthymeleaf.controller;

import com.example.labthymeleaf.database.StudentDB;
import com.example.labthymeleaf.model.PageRespone;
import com.example.labthymeleaf.model.PageResponseIMPL;
import com.example.labthymeleaf.model.Student;
import com.example.labthymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping
    public String getAllStudent(Model model, @RequestParam(required = false) String name) {
        List<Student> NewStudents;
        if (name != null && !name.isEmpty()) {
            NewStudents = StudentDB.students.stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .toList();
        } else {
            NewStudents = StudentDB.students;
        }
        model.addAttribute("students", NewStudents);
        return "index";
    }

    @GetMapping("/student/{id}")
    public String getStudentById(Model model, @PathVariable int id) {
        Student student = StudentDB.students.stream()
                .filter(s -> s.getId() == id).toList().get(0);
        model.addAttribute("students", student);
        return "studentDetail";
    }

    // phân trang

    @GetMapping("/students")
    public String getStudents(Model model,
                              @RequestParam(required = false,defaultValue = "1") int page,
                              @RequestParam(required = false,defaultValue = "5") int limit) {

        PageRespone<Student> pageRespone = PageResponseIMPL.<Student>builder()
            .currentPage(page)
            .pageSize(limit)
            .data(StudentDB.students)
            .build();

        model.addAttribute("pageRespone", pageRespone);
        return "students";
    }













    @GetMapping("student/name/{name}")
    public List<Student> getStudentByName(@PathVariable String name) {
        return studentService.findStudentByName(name);
    }

    @GetMapping("student/year/{fromYear}/{toYear}")
    public List<Student> getStudentByYear(@PathVariable int fromYear, @PathVariable int toYear) {
        return studentService.findStudentByYear(fromYear, toYear);
    }
}
