package com.epam.library.util;

public class ConstantDAO {
    public static final String ADD_INTO_BOOK = "INSERT INTO BOOK(IDBOOK, LANGUAGE, TITLE, ISBN, NUMBER, QUANTITY) VALUES(?,?,?,?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE BOOK SET IDBOOK=?, LANGUAGE=?, TITLE=?, ISBN=?, NUMBER=?, QUANTITY=? WHERE ID=?";
    public static final String GET_ALL_USERS = "SELECT * FROM USER";
    public static final String GET_USER_BY_MAIL = "SELECT * FROM USER WHERE MAIL=?";
    public static final String GET_ALL_READER_LIBRARIAN_PASSWORDS = "SELECT READER.PASSWORD, LIBRARIAN.PASSWORD FROM READER LEFT JOIN LIBRARIAN ON " +
        "LIBRARIAN.PASSWORD=READER.PASSWORD UNION SELECT LIBRARIAN.PASSWORD, READER.PASSWORD FROM READER RIGHT JOIN LIBRARIAN ON LIBRARIAN.PASSWORD = READER.PASSWORD";
//  public static final String GET_ALL_READER_LIBRARIAN_LOGINS = "SELECT READER.MAIL, LIBRARIAN.MAIL FROM READER LEFT JOIN LIBRARIAN ON " +
//            "LIBRARIAN.MAIL=READER.MAIL UNION SELECT LIBRARIAN.MAIL, READER.MAIL FROM READER RIGHT JOIN LIBRARIAN ON LIBRARIAN.MAIL = READER.MAIL";

    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String
    //public static final String



}
