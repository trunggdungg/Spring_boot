package com.example.demo.utils;

import com.example.demo.model.Book;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class ExcelFileReader implements IFileReader{
    /**
     * @param path
     * @return
     */
    @Override// sử dụng thư viên apache POI
    public List<Book> readFile(String path) {
        List<Book> books = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }

                Book book = new Book();
                book.setId((int) row.getCell(0).getNumericCellValue());
                book.setTitle(row.getCell(1).getStringCellValue());
                book.setAuthor(row.getCell(2).getStringCellValue());
                book.setYear((int) row.getCell(3).getNumericCellValue());

                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return books;
    }
}
