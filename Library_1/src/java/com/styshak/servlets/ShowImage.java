package com.styshak.servlets;

import com.styshak.beans.Book;
import com.styshak.utils.BookUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergey
 */
public class ShowImage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");
        try (OutputStream out = response.getOutputStream()) {
            int book_id = Integer.valueOf(request.getParameter("book_id"));
            List<Book> list = (List<Book>) request.getSession(false).getAttribute("currentBookList");
            Book book = BookUtils.getBookById(list, book_id);
            if (book != null) {
                response.setContentLength(book.getImage().length);
                out.write(book.getImage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
