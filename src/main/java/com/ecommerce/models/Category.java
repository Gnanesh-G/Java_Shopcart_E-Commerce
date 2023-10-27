package com.ecommerce.models;

import java.sql.Timestamp;

public class Category {
    private Timestamp id;
    private String categoryName;

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
