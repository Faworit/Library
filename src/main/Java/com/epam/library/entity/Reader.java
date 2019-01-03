package com.epam.library.entity;

public class Reader extends Person {
    private int libraryCard;
    private String password;
    private boolean block;

    public Reader(String name, String surname, String birthDay, String mail, String telephone, int libraryCard, String password, boolean block) {
        super(name, surname, birthDay, mail, telephone);
        this.libraryCard = libraryCard;
        this.password = password;
        this.block = block;
    }

    public int getLibraryCard() {
        return libraryCard;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBlock() {
        return block;
    }

    public void setLibraryCard(int libraryCard) {
        this.libraryCard = libraryCard;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }
}
