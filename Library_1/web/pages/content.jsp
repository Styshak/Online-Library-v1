
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.media.js"></script> 
    </head>
    <body>
        
        <%
            session.setAttribute("tmpCurrentBookList", session.getAttribute("currentBookList"));
        %>
        
        <div style="margin:30px;">
            <a href="book.jsp"><< вернуться</a>
        </div>
        
        <div class="pdf_viewer">
            <object type="application/pdf" style="width:850px; height:900px;" >
                <param name="src" value="<%=request.getContextPath()%>/PDFContent?book_id=<%=request.getParameter("book_id")%>"> 
            </object>
        </div>
    </body>
</html>