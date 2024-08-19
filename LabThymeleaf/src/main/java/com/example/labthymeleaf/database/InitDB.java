package com.example.labthymeleaf.database;

import com.example.labthymeleaf.untils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Đọc dữ liệu từ file json ");
        StudentDB.students =fileReader.readStudentData("student.json");
        System.out.println("count: "+StudentDB.students.size());
    }
}
