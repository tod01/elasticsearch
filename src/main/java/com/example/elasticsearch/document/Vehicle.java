package com.example.elasticsearch.document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Vehicle {

    private String id;
    private String number;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public String getName() {
        return name;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }
}
