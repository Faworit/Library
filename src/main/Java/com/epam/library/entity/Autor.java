package com.epam.library.entity;

public class Autor {
    private int IDAutor;
    private String name;
    private String surname;

    public Autor(int IDAutor, String name, String surname) {
        this.IDAutor = IDAutor;
        this.name = name;
        this.surname = surname;
    }

    public int getIDAutor() {
        return IDAutor;
    }

    public void setIDAutor(int IDAutor) {
        this.IDAutor = IDAutor;
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
                "IDAutor=" + IDAutor +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
