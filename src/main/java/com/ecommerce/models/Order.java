package com.ecommerce.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    private Timestamp id;
    private Date date;
    private User user;
    private ArrayList<Cart> cart;

    public Timestamp getId() {
        return id;
    }

    public void setId(Timestamp id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Cart> cart) {
        this.cart = cart;
    }
}
