package ru.template.example.common;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.function.Function;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PageConverter {

    public static PageRequest toPageRequest(Integer page, Integer size, String sortPropertyName, String sortDirection) {
        var sort = Sort.by(Sort.Direction.fromString(sortDirection.toUpperCase()), sortPropertyName);
        return PageRequest.of(page, size, sort);
    }

    public static <E, D> PageResponse<D> toPageResponse(Page<E> page, Function<E, D> convertFunction) {
        var content = page.get()
                .map(convertFunction)
                .collect(Collectors.toList());
        return PageResponse.<D>builder()
                .content(content)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .size(page.getSize())
                .number(page.getNumber())
                .build();
    }
}
