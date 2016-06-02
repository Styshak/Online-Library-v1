package com.styshak.servlets;

import com.styshak.beans.Book;
import com.styshak.db.DataAccess;
import com.styshak.db.MySQLDataAccessImpl;
import com.styshak.utils.BookUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sergey
 */
@WebServlet(name = "PDFContent", urlPatterns = {"/PDFContent"})
public class PDFContent extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("application/pdf");
         DataAccess da = new MySQLDataAccessImpl();
        try (OutputStream out = response.getOutputStream()) {
            int book_id = Integer.valueOf(request.getParameter("book_id"));
            HttpSession session = request.getSession();        
            List<Book> list = (List<Book>) session.getAttribute("currentBookList");
            Book book = BookUtils.getBookById(list, book_id);
            if(book != null) {
                book.setContent(da.getBookContent(book_id));
                response.setContentLength(book.getContent().length);
                out.write(book.getContent());
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
