package com.epam.library.entity;

public abstract class Literature {
    private String title;
    private int languageID;
    private int ID;

    public Literature(String title, int languageID, int ID) {
        this.title = title;
        this.languageID = languageID;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Literature{" +
                "title='" + title + '\'' +
                ", languageID=" + languageID +
                ", ID=" + ID +
                '}';
    }
}
