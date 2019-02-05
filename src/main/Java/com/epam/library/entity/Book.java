package com.epam.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Book extends Literature {
    private String ISBN;
    private int quantity;
    private List<Autor> autors = new ArrayList<>();
    private List<Genre> geners = new ArrayList<>();

    public Book(String title, int languageID, int ID) {
        super(title, languageID, ID);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public void setAutors(List<Autor> autors) {
        this.autors = autors;
    }

    public List<Genre> getGeners() {
        return geners;
    }

    public void setGeners(List<Genre> geners) {
        this.geners = geners;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", quantity=" + quantity +
                ", autors=" + autors +
                ", geners=" + geners +
                '}';
    }
}
