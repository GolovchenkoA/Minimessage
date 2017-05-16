<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 13.04.2017
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
  <h2>Welcome to Minimessages application</h2>
  <h3>Look at last messages</h3>
  <ol class="main-minimessages-list">
    <c:forEach var="message" items="${messages}">

      <s:url value="/accounts/{accountLogin}"
             var="account_url" >
        <s:param name="accountLogin"
                 value="${message.account.username}" />
      </s:url>

      <li><span class="minimessageListImage">
        <img src=
                     "http://s3.amazonaws.com/accountImages/${message.account.id}.jpg"
             width="48"
             border="0"
             align="middle"
             onError=
                     "this.src='<s:url value="/resources/images"/>/account_avatar.png';"/>
      </span>
      <span class="minimessageListText">
        <a href="${account_url}">
          <c:out value="${message.account.username}" /></a>
          - <c:out value="${message.text}" /><br/>
         <small><fmt:formatDate value="${message.created}"
                                pattern="hh:mma MMM d, yyyy" /></small>
      </span></li>
    </c:forEach>
  </ol>
</div>

