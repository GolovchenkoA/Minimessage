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

    <s:url value="/messages" var="messages_url" />

    <form:form modelAttribute="message" method="POST" action="${messages_url}">
      <form:label path="text"><s:message code="label.message" text="Enter message:"/></form:label>
      <form:textarea path="text" rows="2" cols="40" />
      <form:errors path="text" />

      <br/>
      <div class="spitItSubmitIt">
        <input type="submit" value="Send" class="status-btn round-btn disabled" />
      </div>
    </form:form>
  </sec:authorize>

</div>
