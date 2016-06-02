/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.styshak.db;

import com.styshak.beans.Author;
import com.styshak.beans.Book;
import com.styshak.beans.Genre;
import com.styshak.enums.SearchType;
import java.util.List;

/**
 *
 * @author Sergey
 */
public interface DataAccess {

    public List<Author> getAuthorsList();
    
    public List<Genre> getGenresList();

    public List<Book> getBookListByGenreId(int id);
    
    public List<Book> getBookListByLetter(String letter);
    
    public List<Book> getBookListBySearchString(String search_string, SearchType search_option);
    
    public byte[] getBookContent(int id);
}
