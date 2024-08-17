package com.example.demo.model;

import java.util.List;

public interface PageResponse<T>{// T ở đây đại diện cho 1 dataType bất kỳ
    int getCurrentPage();
    int getPageSize();
    int getTotalElements();
    int getTotalPages();
    List<T> getContent();


}
