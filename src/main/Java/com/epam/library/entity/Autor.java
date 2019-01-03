package com.epam.library.entity;

public class Autor {
    private String autorID;
    private String firstNAme;
    private String surName;

    public Autor(String autorID, String firstNAme, String surName) {
        this.autorID = autorID;
        this.firstNAme = firstNAme;
        this.surName = surName;
    }

    public String getAutorID() {
        return autorID;
    }

    public String getFirstNAme() {
        return firstNAme;
    }

    public String getSurName() {
        return surName;
    }

    public void setAutorID(String autorID) {
        this.autorID = autorID;
    }

    public void setFirstNAme(String firstNAme) {
        this.firstNAme = firstNAme;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}
