package com.yan.booksearch;

import java.io.Serializable;

public class Books implements Serializable {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
