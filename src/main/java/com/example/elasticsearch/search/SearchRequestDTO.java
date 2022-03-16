package com.example.elasticsearch.search;

import javax.swing.*;
import java.util.List;

import org.elasticsearch.search.sort.SortOrder;

public class SearchRequestDTO {

    private List<String> fields;
    private String searchTerm;
    private String sortBy;
    private SortOrder order;

    public List<String> getFields() {
        return fields;
    }

    public String getSortBy() {
        return sortBy;
    }

    public SortOrder getOrder() {
        return order;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
