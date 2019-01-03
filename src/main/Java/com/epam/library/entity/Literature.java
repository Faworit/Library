package com.epam.library.entity;

public class Literature {
    private String title;
    private int languageID;
    private int ID;

    public Literature(String title, int languageID, int ID) {
        this.title = title;
        this.languageID = languageID;
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public int getLanguageID() {
        return languageID;
    }

    public int getID() {
        return ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
