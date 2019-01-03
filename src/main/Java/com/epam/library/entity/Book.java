package com.epam.library.entity;

import java.util.ArrayList;

public class Book extends Literature {
    private String genre;
    private ArrayList<Autor> autors;
    private int ISBN;
    private int quantity;

    public Book(String title, int languageID, int ID, String genre, ArrayList<Autor> autors, int ISBN, int quantity) {
        super(title, languageID, ID);
        this.genre = genre;
        this.autors = autors;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Book(String title, int languageID, int ID, ArrayList<Autor> autors, int ISBN, int quantity) {
        super(title, languageID, ID);
        this.autors = autors;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    public ArrayList<Autor> getAutors() {
        return autors;
    }

    public void setAutors(ArrayList<Autor> autors) {
        this.autors = autors;
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
}
