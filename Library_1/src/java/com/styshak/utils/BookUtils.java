package com.styshak.utils;

import com.styshak.beans.Book;
import java.util.List;

/**
 *
 * @author Sergey
 */
public class BookUtils {

    private BookUtils() {

    }

    public static Book getBookById(List<Book> bookList, int book_id) {
        for (Book book : bookList) {
            if (book.getId() == book_id) {
                return book;
            }
        }
        return null;
    }
}
