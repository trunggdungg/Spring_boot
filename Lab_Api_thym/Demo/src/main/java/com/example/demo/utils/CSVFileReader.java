package com.example.demo.utils;

import com.example.demo.model.Book;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CSVFileReader implements IFileReader {

    @Override
    public List<Book> readFile(String path) {
        try (FileReader reader = new FileReader(path)) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(reader)
                .withType(Book.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
