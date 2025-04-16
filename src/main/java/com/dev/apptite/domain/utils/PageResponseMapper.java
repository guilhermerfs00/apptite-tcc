package com.dev.apptite.domain.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageResponseMapper {

    public <T> PageResponse<T> pageToPageResponse(Page<T> page) {

        PageResponse<T> pageResponse = new PageResponse<>();

        pageResponse.setPageNumber(page.getPageable().getPageNumber());
        pageResponse.setPageSize(page.getPageable().getPageSize());
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setContent(page.getContent());

        return pageResponse;
    }

}
