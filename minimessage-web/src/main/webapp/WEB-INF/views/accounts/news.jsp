<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h2>My News</h2>

<div class="subscribe">
    <ol>
        <c:forEach var="message" items="${publishers_messages}">

            <s:url value="/accounts/${message.account.username}" var="account_url" >
                <s:param name="accountLogin" value="${message.account.username}" />
            </s:url>

            <li>
              <span class="minimessageListText">
                <a href="${account_url}">
                    <c:out value="${message.account.username}" /></a>
                    <c:out value="${message.text}" /><br/>
                 <small><fmt:formatDate value="${message.created}"
                                        pattern="hh:mma MMM d, yyyy" /></small>
              </span>
            </li>
        </c:forEach>
    </ol>
</div>