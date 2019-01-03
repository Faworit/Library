package com.epam.library.entity;

public class Person {
    private String name;
    private String surname;
    private String birthDay;
    private String mail;
    private String telephone;

    public Person(String name, String surname, String birthDay, String mail, String telephone) {
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.mail = mail;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getMail() {
        return mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
