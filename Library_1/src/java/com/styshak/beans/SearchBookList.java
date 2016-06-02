package com.styshak.beans;

import com.styshak.db.DataAccess;
import com.styshak.db.MySQLDataAccessImpl;
import com.styshak.enums.SearchType;
import java.util.ArrayList;
import java.util.List;

public class SearchBookList {
    
    private String genre_id;
    private String letter;
    private String search_string;
    private String search_option;
    private DataAccess da = new MySQLDataAccessImpl();

    public String getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(String genre_id) {
        this.genre_id = genre_id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getSearch_string() {
        return search_string;
    }

    public void setSearch_string(String search_string) {
        this.search_string = search_string;
    }

    public String getSearch_option() {
        return search_option;
    }

    public void setSearch_option(String search_option) {
        this.search_option = search_option;
    }
 
    public List<Book> getBooksByRequest() {
        List<Book> booksList = new ArrayList<>();
        
        if(genre_id != null && genre_id.length() > 0) {
            int genreId = Integer.parseInt(genre_id);
            booksList = da.getBookListByGenreId(genreId);
            return booksList;
        } else if(letter != null && letter.length() > 0){
            booksList = da.getBookListByLetter(letter);
            return booksList;
        } else if(search_string != null && search_string.length() > 0){
            SearchType type = SearchType.TITLE;
            if(search_option.equals("Автор")) {
                type = SearchType.AUTHOR;
            }
            booksList = da.getBookListBySearchString(search_string, type);
            return booksList;
        }
        return null;
    }
}
