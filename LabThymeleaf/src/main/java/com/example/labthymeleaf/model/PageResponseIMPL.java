package com.example.labthymeleaf.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseIMPL<T> implements PageRespone<T>{

    int currentPage;// trang hiện tại
    int pageSize; // số lượng phần tử trên 1 trang
    List<T> data; // dữ liệu trên trang hiện tại
    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalPage() {
        return data.size();
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getTotalElement() {
        return (int) Math.ceil((double) data.size() /pageSize);
    }

    @Override
    public List<T> getContent() {
        int fromIndex = (currentPage - 1) * pageSize; // vị trí bắt đầu của trang hiện tại
        int toIndex = fromIndex + pageSize; // vị trí kết thúc của trang hiện tại
        if (toIndex > data.size()) { // nếu vị trí kết thúc lớn hơn tổng số phần tử
            toIndex = data.size(); // thì lấy tất cả phần tử còn lại
        }
        return data.subList(fromIndex, toIndex); // trả về phần tử của trang hiện tại
    }
}
