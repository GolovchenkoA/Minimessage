<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 15.04.2017
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2>Account " ${login} " info </h2>


<div class="subscribe">
  <table>
      <tr >
          <td >
            <%--<form:form  action="/accounts/subscribe_to/${login}" method="POST">--%> <%--.  work on windows (Tomcat 7) / doesn't work on linux (Apache Tomcat/7.0.68 (Ubuntu)).  --%>
            <form:form  action="${pageContext.request.contextPath}/accounts/subscribe_to/${login}" method="POST">
              <%--<input type="button" id="subscribe_to_publisher" name="subscribe" value="subscribe">--%>
              <input type="submit" id="subscribe_to_publisher" name="subscribe" value="subscribe">
            </form:form >
          </td>
          <td>
            <form action="${pageContext.request.contextPath}/accounts/unsubscribe_from/${login}" method="POST">
              <input type="submit" id="unsubscribe_from_publisher" name="unsubscribe" value="unsubscribe">
            </form>
          </td>
      </tr>
  </table>
</div>


<div>

  <h3>Last messages</h3>

  <table cellspacing="15" class="user-minimessages-list horizontalRuled" border-collapse: collapse>
    <thead align="right">
    <tr>
      <th>Message</th>
      <th>Date</th>
    </tr>
    </thead>
    <c:forEach var="message" items="${messages}">
      <tr>
        <td><c:out value="${message.text}"/></td>
        <td><fmt:formatDate value="${message.created}" pattern="hh:mm MMM d, yyyy"/></td>
      </tr>
    </c:forEach>
</div>

