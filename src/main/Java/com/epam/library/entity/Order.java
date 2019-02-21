package com.epam.library.entity;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private String bookTitle;
    private String state;
    private Date orderDate;
    private Date returnDate;
    private Date actuallyReturn;
    private List<User> users;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getActuallyReturn() {
        return actuallyReturn;
    }

    public void setActuallyReturn(Date actuallyReturn) {
        this.actuallyReturn = actuallyReturn;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", bookTitle='" + bookTitle + '\'' +
                ", state='" + state + '\'' +
                ", orderDate=" + orderDate +
                ", returnDate=" + returnDate +
                ", actuallyReturn=" + actuallyReturn +
                ", users=" + users +
                '}';
    }
}
