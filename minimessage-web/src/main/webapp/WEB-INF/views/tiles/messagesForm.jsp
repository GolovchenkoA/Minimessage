<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 17.04.2017
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>


  <sec:authorize access="hasRole('ROLE_USER')">

    <s:url value="/messages/create_new_message" var="messages_url" />
<table>
    <form:form commandName="message" method="POST" action="${messages_url}">
      <tr><td><form:label path="text"><s:message code="label.message" text="Enter message:"/></form:label></td></tr>
      <tr><td><form:textarea path="text" rows="2" cols="40"  /></td></tr>
      <tr><td>max 40 characters</td></tr>
      <tr><td><form:errors path="text" cssClass="error" /></td></tr>
      <tr>
        <td>
        <br/>
        <div class="messageItSubmit">
          <input type="submit" value="Send"/>
        </div>
        </td>
      </tr>
    </form:form>
</table>

  </sec:authorize>

</div>
