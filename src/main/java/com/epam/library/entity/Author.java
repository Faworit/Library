package com.epam.library.entity;

public class Autor {
    private int IDAuthor;
    private String name;
    private String surname;

    public Autor(int IDAuthor, String name, String surname) {
        this.IDAuthor = IDAuthor;
        this.name = name;
        this.surname = surname;
    }

    public int getIDAuthor() {
        return IDAuthor;
    }

    public void setIDAuthor(int IDAuthor) {
        this.IDAuthor = IDAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "IDAuthor=" + IDAuthor +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
