package com.epam.library.entity;

public class Other extends Literature{
    private int edition;
    private int quantity;
    private String genre;

    public Other(String title, int languageID, int ID, int edition, int quantity, String genre) {
        super(title, languageID, ID);
        this.edition = edition;
        this.quantity = quantity;
        this.genre = genre;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
