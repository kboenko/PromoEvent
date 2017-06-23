<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
    Document   : results
    Created on : 04.05.2016, 13:25:04
    Author     : kboenko
--%>

<%@page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <%--base href="<c:url value="/"/>"--%>
        <link rel="shortcut icon" href="<c:url value="/css/images/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/css/images/favicon.ico"/>" type="image/x-icon">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Результаты проведения акции</title>
        <style>
            #control {
              border: 4px double black;
              background: whitesmoke;
              width: 800px;
              padding: 10px 10px 10px 10px;
            }

            #t1 {
              width: 800px;
              align-self: center;
            }
            table, th, td {
              border: 1px solid black;
              border-collapse: collapse;

            }
            td {
              padding: 5px;
              text-align: left;
            }

            table#t1 th	{
              background-color: gray;
              color: white;
            }
            table#t1 tr:nth-child(even) {
              background-color: #eee;
            }
            table#t1 tr:nth-child(odd) {
              background-color:#fff;
            }
                       
        </style>
    </head>
    <body>
        
        <div id="result">
                <img id="logo" src="<c:url value="css/images/logo.png"/>">
                <%--img src="<%=request.getContextPath()%>/css/images/logo.png"/--%>
                <%--img id="logo" src="<c:url value="${pageContext.servletContext.contextPath}/css/images/logo.png"/>"--%>
                <hr>
                <h1 align="center">Результаты проведения акции:</h1>
        </div></br>
        <div id="content">
            <table id="t1" align="center" border="1">
                    <thead>
                    <tr>
                      <th>№</th>
                      <th>Дата</th>
                      <th>Номер тел.</th>
                      <th>Приз</th>
                      <th>Выдача приза</th>
                    </tr>
                    </thead>
                    <tbody>
              </tbody>
            <%--/table>
                    <table border="1" align="center"--%>
                        <c:forEach items="${requestScope.entities}" var="entity">
                            <tr>
                                <td id="needTo"><c:out value="${entity.id}"/></td>
                                <td><c:out value="${entity.date}"/></td>
                                <td><c:out value="${entity.callerid}"/></td>
                                <td><c:out value="${entity.prize}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty entity.prize_taken}">
                                            <form action="update" method="POST">
                                                <input id="updBut" type="submit" value="Выдать">
                                                <select id="select" name = "shop" size="1">
                                                    <c:forEach items="${points}" var="pt">
                                                        <option value="${pt}">${pt}</option>>
                                                    </c:forEach>
                                                </select>
                                                <input id = "hdn" type="hidden" name="id" value="${entity.id}">
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${entity.prize_taken}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
        </div>    
    </body>
</html>
