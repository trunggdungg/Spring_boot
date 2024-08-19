package com.example.ktra_lab.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseIMPL<T> implements PageResponse<T> {

    int currentPage;// trang hien tai
    int pageSize;// so phan tu tren 1 trang
    List<T> data;// danh sach phan tu
    @Override
    public int getTotalPage() {
        return data.size();
    }

    @Override
    public int getTotalElement() {
        return (int) Math.ceil((double) data.size() /pageSize);
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public List<T> getData() {
        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = fromIndex + pageSize;
        if (toIndex > data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);

    }
}
