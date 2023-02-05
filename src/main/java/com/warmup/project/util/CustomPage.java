package com.warmup.project.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CustomPage<T> {
    private List<T> content;
    private int size;
    private int page;
    private Sort sort;
    private int totalPages;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    private long totalElements;
    public CustomPage(Page page){
        this.content = page.getContent();
        this.size= page.getSize();
        this.sort = page.getSort();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber();
        this.totalElements = page.getTotalElements();
    }

}
