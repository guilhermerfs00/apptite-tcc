package com.dev.apptite.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T>{

    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private List<T> content;

}
