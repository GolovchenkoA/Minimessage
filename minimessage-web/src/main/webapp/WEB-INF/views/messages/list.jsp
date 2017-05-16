<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 14.04.2017
  Time: 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
  <h2>${account.username}'s messages</h2>
  <%--alternative--%>
  <%--<h2>Messages for <%= request.getParameter("account") %></h2>--%>


<%--  <script>
    function deleteMessageid) {
      if(confirm("Are you sure you want to delete this message?")) {
        document["deleteMessage_" + id].submit();
      }
    }
  </script>--%>

  <table class="user-minimessages-list horizontalRuled">
    <thead>
      <tr>
        <th>Date</th>
        <th>Message</th>
      </tr>
    </thead>

    <c:forEach items="${messagesList}" var="message">
      <s:url value="/accounts/${message.account.username}" var="account_url"/>
      <tr>
        <td><small><fmt:formatDate value="${message.created}" pattern="hh:mm MMM d, yyyy" /></small>
        </td>
        <td>
            <c:out value="${message.text}"/>
        </td>
<%--          <s:url value="/spittles/${spittle.id}" var="spittle_url" />
          <sf:form method="delete" action="${spittle_url}"
                   name="deleteSpittle_${spittle.id}"
                   cssClass="deleteForm">
            <input type="submit" value="Delete"/>
          </sf:form>--%>
        </td>
      </tr>
    </c:forEach>


</div>
