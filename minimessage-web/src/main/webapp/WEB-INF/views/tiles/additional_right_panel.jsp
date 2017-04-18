<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 17.04.2017
  Time: 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="logout">
    <ul>
        <%--Main users menu menu   --%>
        <li><a href="<s:url value="/j_spring_security_logout" />">Logout</a></li>
<%--        <li><a href="<s:url value="/accounts?username={login}" />">my account</a></li>
        <li><a href="<s:url value="/accounts?username={j_username}" />">my account</a></li>--%>

        <%--Additional Admin menu   --%>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<s:url value="/admin/accounts" />">All Accounts</a></li>
            <li><a href="<s:url value="/admin/roles" />">All Roles</a></li>
        </security:authorize>
    </ul>
</div>