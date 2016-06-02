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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergey
 */
public class MySQLDataAccessImpl implements DataAccess {

    private List<Author> authorList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();

    private List<Author> getAuthors() {
        String sql = "select * from author order by fio";
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
                Author author = new Author(rs.getInt(1), rs.getString(2));
                authorList.add(author);
            }
        } catch (SQLException e) {
        }
        return authorList;
    }

    @Override
    public List<Author> getAuthorsList() {
        if (authorList.isEmpty()) {
            return getAuthors();
        } else {
            return authorList;
        }
    }

    private List<Book> getBooks(String query) {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"), rs.getString("name"),
                        rs.getInt("page_count"),
                        rs.getString("isbn"), rs.getString("genre"),
                        rs.getString("author"), rs.getInt("publish_year"),
                        rs.getString("publisher"),
                        rs.getBytes("image"));
                bookList.add(book);
            }
        } catch (Exception e) {
            int a = 5;
        }
        return bookList;
    }

    @Override
    public List<Book> getBookListByGenreId(int id) {
        bookList.clear();
        StringBuilder sql = new StringBuilder();
        String mainSql = "select b.id, b.name, b.content, b.page_count, b.isbn, "
                + "g.name as genre, a.fio as author, b.publish_year, p.name as publisher, "
                + "b.image "
                + "from book as b "
                + "inner join genre as g on g.id = b.genre_id "
                + "inner join author as a on a.id = b.author_id "
                + "inner join publisher as p on p.id = b.publisher_id ";
        sql.append(mainSql);
        if (id != 0) {
            sql.append("where b.genre_id=" + id);
        }
        sql.append(" order by b.name");
        return getBooks(sql.toString());
    }

    private List<Genre> getGenres() {
        String sql = "select * from genre order by name";
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Genre genre = new Genre(rs.getInt(1), rs.getString(2));
                genreList.add(genre);
            }
        } catch (SQLException e) {

        }
        return genreList;
    }

    @Override
    public List<Genre> getGenresList() {
        if (genreList.isEmpty()) {
            return getGenres();
        } else {
            return genreList;
        }
    }

    @Override
    public List<Book> getBookListByLetter(String letter) {
        bookList.clear();
        String sql = "select b.id, b.name, b.content, b.page_count, b.isbn, "
                + "g.name as genre, a.fio as author, b.publish_year, p.name as publisher, "
                + "b.image "
                + "from book as b "
                + "inner join genre as g on g.id = b.genre_id "
                + "inner join author as a on a.id = b.author_id "
                + "inner join publisher as p on p.id = b.publisher_id "
                + "where b.name like " + "'" + letter + "%' "
                + "order by b.name";
        return getBooks(sql);
    }

    @Override
    public List<Book> getBookListBySearchString(String search_string, SearchType search_option) {
        bookList.clear();
        StringBuilder sql = new StringBuilder();
        String mainSql = "select b.id, b.name, b.content, b.page_count, b.isbn, "
                + "g.name as genre, a.fio as author, b.publish_year, p.name as publisher, "
                + "b.image "
                + "from book as b "
                + "inner join genre as g on g.id = b.genre_id "
                + "inner join author as a on a.id = b.author_id "
                + "inner join publisher as p on p.id = b.publisher_id ";
        sql.append(mainSql);
        if (search_option == SearchType.TITLE) {
            sql.append("where b.name like " + "'%" + search_string + "%' ");
        } else {
            sql.append("where a.fio like " + "'%" + search_string + "%' ");
        }
        sql.append("order by b.name");
        return getBooks(sql.toString());
    }

    @Override
    public byte[] getBookContent(int id) {
        byte[] content = null;
        String sql = "select content from book where Id = ?";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                content = rs.getBytes("content");
            }
        } catch(Exception e) {
            
        }
        return content;
    }
}
