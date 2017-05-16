<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 13.04.2017
  Time: 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Minimessages</title>
  <link href="<s:url value="/resources" />/css/minimessages.css"
        rel="stylesheet"
        type="text/css" />

  <script type="text/javascript" src="../../resources/js/showMenu.js"></script>
  <script type="text/javascript" src="../../resources/js/subscribe_buttons.js"></script>

</head>

<body>


<security:authorize access="hasRole('ROLE_USER')">
 <%--Menu on bottom   --%>
  <div class="dropdown">
    <button onclick="showMenu()" class="dropbutton">Menu</button>
    <div id="myDropdown" class="dropdown-content">
      <a href="<s:url value="/" />">Main Page</a>
      <a href="<s:url value="/messages/MyMessages" />">My Messages</a>
      <a href="<s:url value="/j_spring_security_logout" />">Logout</a>
    </div>
  </div>
</security:authorize>




<div id="container">
  <a href="<s:url value="/home" />"><img id="application_logo" title="minimessage_logo.png" src="<s:url value="/resources" />/images/minimessage_logo.png" border="0"/></a>
    <div id="top">
      <t:insertAttribute name="top" />
    </div>
    <div id="side">
      <t:insertAttribute name="side" />
    </div>
    <div id="content">
      <t:insertAttribute name="content" />
    </div>
</div>
</body>
</html>
