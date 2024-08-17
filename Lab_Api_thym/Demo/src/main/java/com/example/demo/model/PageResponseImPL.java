package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponseImPL<T> implements PageResponse<T>{

    int currentPage;
    int pageSize;
    List<T> data;
    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getTotalElements() {
        return data.size();
    }

    @Override
    public int getTotalPages() {
        return (int) Math.ceil((double) data.size() /pageSize);// vif số trang là số nguyên nên phải ép kiểu và làm tròn lên
    }

    @Override
    public List<T> getContent() {
         int fromIndex = (currentPage - 1) * pageSize;// vị trí bắt đầu của trang hiện tại đến endInde là vị trí kết thúc
        if (fromIndex >= data.size()) {
            return List.of();
        }
        int toIndex = Math.min(fromIndex + pageSize, data.size());
        return data.subList(fromIndex, toIndex);// cắt data trả về 1 list mới từ fromIndex đến toIndex
    }
}
