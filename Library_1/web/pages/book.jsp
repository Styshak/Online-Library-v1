<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@include file="../WEB-INF/jspf/left_menu.jspf" %>
<%@include file="../WEB-INF/jspf/letters.jspf" %>
<div class="book_list">

    <% 
        request.setCharacterEncoding("UTF-8");  
    %>
    
    <jsp:useBean id="bookList" class="com.styshak.beans.SearchBookList" scope="page"/>
    <jsp:setProperty name="bookList" property="genre_id" value="${param.genre_id}" />
    <jsp:setProperty name="bookList" property="letter" value="${param.letter}" />
    <jsp:setProperty name="bookList" property="search_string" value="${param.search_string}" />
    <jsp:setProperty name="bookList" property="search_option" value="${param.search_option}" />
    
    <%
        
        if(session.getAttribute("tmpCurrentBookList") != null) {
            session.setAttribute("currentBookList", session.getAttribute("tmpCurrentBookList"));
            session.setAttribute("tmpCurrentBookList", null);
        } else {
            session.setAttribute("currentBookList", bookList.getBooksByRequest());
        }
    %>


    <h3>${param.genre_name}</h3>
    <c:forEach var="book" items="${currentBookList}">
        <div class="book_info">
            <div class="book_title">
                <p> ${book.getName()}</p>
            </div>
            <div class="book_image">
                <img src="<%=request.getContextPath()%>/ShowImage?book_id=${book.getId()}" height="250" width="190" alt="Обложка"/>
            </div>
            <div class="book_details">
                <br><strong>ISBN:</strong> ${book.getIsbn()}
                <br><strong>Издательство:</strong> ${book.getPublisher()}
                <br><strong>Количество страниц:</strong> ${book.getPageCount()}
                <br><strong>Дата издания:</strong> ${book.getPublishYear()}
                <br><strong>Автор:</strong> ${book.getAuthor()}
                <p style="margin:10px;"> <a href="content.jsp?book_id=${book.getId()}">Читать</a></p>
            </div>
        </div>
    </c:forEach>
</div>