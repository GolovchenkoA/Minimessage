<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 15.04.2017
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h2>Account " ${login} " info </h2>

<div class="subscribe">
  <table>
      <tr >
          <td >
            <form:form  action="/accounts/subscribe_to/${login}" method="POST">
              <%--<input type="button" id="subscribe_to_publisher" name="subscribe" value="subscribe">--%>
              <input type="submit" id="subscribe_to_publisher" name="subscribe" value="subscribe">
            </form:form >
          </td>
          <td>
            <form action="/accounts/unsubscribe_from/${login}" method="POST">
              <input type="submit" id="unsubscribe_from_publisher" name="unsubscribe" value="unsubscribe">
            </form>
          </td>
      </tr>
  </table>
</div>


<div>

  <h3>Last messages</h3>

  <table cellspacing="15" class="horizontalRuled" border-collapse: collapse>
    <thead align="right">
    <tr>
      <th>Message</th>
      <th>Date</th>
    </tr>
    </thead>
    <c:forEach var="message" items="${messages}">
      <tr>
        <td><c:out value="${message.text}"/></td>
        <td></td><fmt:formatDate value="${message.created}" pattern="hh:mm MMM d, yyyy" /></td>
      </tr>
    </c:forEach>
</div>

