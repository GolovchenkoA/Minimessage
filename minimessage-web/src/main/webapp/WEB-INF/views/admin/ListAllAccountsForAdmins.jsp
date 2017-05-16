<%@ page import="java.util.Collections" %>
<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 18.04.2017
  Time: 0:31
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>

    <h2>Accounts</h2>


    <table class="accounts_list" border="1" style="border-collapse: collapse">

      <tr>
        <th>id</th>
        <th>Account name</th>
      </tr>

      <c:forEach var="acccount" items="${accounts}">
        <tr>
          <td><c:out value="${acccount.id}"/></td>
          <td><c:out value="${acccount.username}"/></td>
        </tr>
      </c:forEach>

    </table>
  </div>