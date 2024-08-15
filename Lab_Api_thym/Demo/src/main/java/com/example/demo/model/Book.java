package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor// contructer ko tham số
@AllArgsConstructor// full tham số
@Getter
@Setter
@ToString
@Builder// design buuton thuộc nhóm khởi tạo
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)// có thể xóa private của thuộc tính
public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
}
