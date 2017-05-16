<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<h2>All accounts</h2>

<div>

    <c:choose>
        <c:when test="${not empty accounts}">
            <table class="accounts_list" cellspacing="15" class="horizontalRuled" border-collapse: collapse>
                <thead align="right">
                <tr>
                    <th>Login</th>
                </tr>
                </thead>
<%--                <c:forEach var="account" items="${accounts}">
                    <tr >
                        <form commandName="publisher_account" action="/accounts/subscribe_to_publisher" method="POST">
                            <td class="login_in_table"><c:out value="${account.username}"/></td>
                            <td>
                                &lt;%&ndash;<input type="hidden" value="${account.username}" name="account_login"/>&ndash;%&gt;
                                <input type="button" onclick="subscribeajax()" class="subscribe_button" name="subscribe" value="subscribe">

                            </td>
&lt;%&ndash;                            <td><button class="subscribe_button" onclick="subscribe()">subscribe</button></td>
                            <td><button class="unsubscribe_button" onclick="unsubscribe()">unsubscribe</button></td>&ndash;%&gt;
                        </form>

                    </tr>
&lt;%&ndash;                    <tr >
                        <td class="login_in_table"><c:out value="${account.username}"/></td>
                        <td><button class="subscribe_button" onclick="subscribe()">subscribe</button></td>
                        <td><button class="unsubscribe_button" onclick="unsubscribe()">unsubscribe</button></td>
                    </tr>&ndash;%&gt;
                </c:forEach>
            </table>--%>

            <table class="accounts_list" border="1" style="border-collapse: collapse">

                <tr>
                    <th>id</th>
                    <th>Account name</th>
                </tr>

                <c:forEach var="account" items="${accounts}">
                <s:url value="/accounts/${account.username}" var="account_url"/>

                    <tr>
                        <td><c:out value="${acccount.id}"/></td>
                        <%--<td><c:out value="${acccount.username}"/></td>--%>
                        <td><a href="${account_url}"><c:out value="${account.username}"/></a></td>
                    </tr>
                </c:forEach>

            </table>
        </c:when>
        <c:otherwise>
            <div class="error" >Cant find any account</div>
        </c:otherwise>
    </c:choose>



</div>