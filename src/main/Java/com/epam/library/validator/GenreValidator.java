package com.epam.library.validator;

import com.epam.library.connectionPool.BookDAO;
import com.epam.library.connectionPool.GenreDAO;
import com.epam.library.entity.Genre;

import java.util.List;

public class GenreValidator {
    public static boolean checkExistingGenreID(Object checkedObject, int languageID){
        boolean isExists = false;
        List<Genre> genres;
        Genre genre;
        GenreDAO genreDAO = new GenreDAO();
        genres = genreDAO.getAllGenres(languageID);
        for (int i = 0; i < genres.size(); i++) {
            int idGenreDB;
            String genreName;
            genre = genres.get(i);
            idGenreDB = genre.getIDGenre();
            genreName = genre.getGenreName();
            if(checkedObject.equals(idGenreDB) || checkedObject.equals(genreName)){
                isExists = true;
            }
        }           
        return isExists;
    } 
}
