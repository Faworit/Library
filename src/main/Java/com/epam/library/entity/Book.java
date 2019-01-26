package com.epam.library.entity;

import java.util.ArrayList;

public class Book extends Literature {
    private int ISBN;
    private int quantity;
    private ArrayList<Autor> autors;
    private ArrayList<Genre> geners;

    public Book(String title, int languageID, int ID) {
        super(title, languageID, ID);
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Autor> getAutors() {
        return autors;
    }

    public void setAutors(ArrayList<Autor> autors) {
        this.autors = autors;
    }

    public ArrayList<Genre> getGeners() {
        return geners;
    }

    public void setGeners(ArrayList<Genre> geners) {
        this.geners = geners;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", quantity=" + quantity +
                ", autors=" + autors +
                ", geners=" + geners +
                ", title=" +
                '}';
    }
}
