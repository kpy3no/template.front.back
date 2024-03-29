package ru.template.example.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponse<T> {
    List<T> content;
    int totalPages;
    long totalElements;
    int size;
    int number;
}
