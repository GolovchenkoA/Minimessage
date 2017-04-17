<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 15.04.2017
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>Account</h2>

if(account != null){
  <h3>${account.username}</h3>
  <p>${account.password}</p>
} else{
<p>account not found</p>
}
