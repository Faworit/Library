package com.epam.library.entity;

public class Librarian extends Person{
    private int LibrarianID;
    private String password;

    public Librarian(String name, String surname, String birthDay, String mail, String telephone, int librarianID, String password) {
        super(name, surname, birthDay, mail, telephone);
        LibrarianID = librarianID;
        this.password = password;
    }

    public int getLibrarianID() {
        return LibrarianID;
    }

    public String getPassword() {
        return password;
    }

    public void setLibrarianID(int librarianID) {
        LibrarianID = librarianID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
