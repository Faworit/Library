package com.epam.library.util;

public class ConstantDAO {
    public static final String ADD_INTO_BOOK = "INSERT INTO BOOK(IDBOOK, LANGUAGE, TITLE, ISBN, NUMBER, QUANTITY) VALUES(?,?,?,?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE BOOK SET IDBOOK=?, LANGUAGE=?, TITLE=?, ISBN=?, NUMBER=?, QUANTITY=? WHERE ID=?";
    public static final String GET_BOOK = "SELECT TITLE, GENRENAME, QUANTITY, SURNAME, NAME BROM BOOK B, AUTOR A, BOOK2AUTOR BA, GENRE G, BOOK2GENRE BG WHERE B.IDBOOK = BA.IDBOOK AND" +
            " A.AUTORID = BA.AUTORID AND G.IDLANGUAGE = 1 AND B.IDBOOK=BG.IDBOOK AND G.IDGENRE=BG.IDGENRE AND BG.IDLANGUAGE=G.IDLANGUAGE AND B.LANGUAGE=BG.IDLANGUAGE;";
  //с этим глючит // GET_USER_BY_MAIL_PASSWORD = "SELECT ID_USER, PASSWORD, NAME, SURNAME, MAIL, TELEPHONE, BIRTH_DAY, BLOCK, ROLE_NAME FROM USER U, ROLE R WHERE MAIL=? AND PASSWORD=? AND U.ROLE=R.ID_ROLE";
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
