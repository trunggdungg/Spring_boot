package com.example.labthymeleaf.model;

import java.util.List;

public interface PageRespone<T> {
    int getPageSize();
    int getTotalPage();
    int getCurrentPage();
    int getTotalElement();
    List<T> getContent();
}
