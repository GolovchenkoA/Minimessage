<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 17.04.2017
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
  <h2>Roles management panel</h2>

  <form:form method="POST" commandName="role" >
    <table>
      <tr>
        <td>Role name</td>
        <td><form:input path="name" maxlength="25" title="example: ROLE_ADMIN or ROLE_USER"/></td>
          <%--<small>Must be alphanumeric with no spaces. Can contain underscores. Min 4 and Max 25 characters</small>--%>
          <%--<form:errors path="username" cssClass="error" /></td>--%>
      </tr>

        <td colspan="3"><input type="submit" value="Create new Role" /></td>

    </table>
    <form:errors path="*" cssClass="error" />
  </form:form>




  <h2>Roles</h2>

    <table border="1" style="border-collapse: collapse">

      <tr>
        <th>id</th>
        <th>Role name</th>
      </tr>

      <c:forEach var="role" items="${roles}">
        <tr>
          <td><c:out value="${role.id}"/></td>
          <td><c:out value="${role.name}"/></td>
        </tr>
      </c:forEach>

    </table>

</div>
