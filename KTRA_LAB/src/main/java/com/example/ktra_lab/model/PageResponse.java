package com.example.ktra_lab.model;


import java.util.List;

public interface PageResponse<T> {
    int getTotalPage();
    int getTotalElement();
    int getCurrentPage();
    int getPageSize();
    List<T> getData();
}
