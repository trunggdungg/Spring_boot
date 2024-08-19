package com.example.labthymeleaf.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Student {
int id;
String name;
int year;
String email;
String phone;
String address;
}
