<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div>
  <%--<sec:authorize access="!isAuthenticated()">--%>

    <div class="msg">
      <h3>Sign In!</h3>
    </div>

        <%--work only on OS Windows--%>
    <%--<form class="signin" action="<s:url value="/static/j_spring_security_check"/>" method="post">--%>
         <%--Work on OS Windows and Linux --%>
      <form class="signin" action="<s:url value="/j_spring_security_check"/>" method="post">
    <%--<form class="signin"  method="post">--%>
      <fieldset>
        <div>
            <label for="j_username">Login</label>
          <input id="login" type="text" name="j_username"/>
        </div>
        <div>
            <label for="j_password">Password</label>
          <input id="pass" type="password" name="j_password"/>
        </div>
          <%--		    <input id="remember_me" type="checkbox" value="1"
                             name="_spring_security_remember_me"/>
                      <label for="remember_me">Remember me</label>--%>
          <%--		    <small>
                        <a href="/account/resend_password">Forgot?</a>
                      </small>--%>
        <input id="submit" type="submit" value="Sign In!"
               name="commit"/>
      </fieldset>
    </form>
    		<div class="notify">
                Do you want a new account?
              <br/>
              <a class="join" href="<s:url value="/accounts/create_new_account"/>">Create new account!</a>
              <br/>
            </div>
  <%--</sec:authorize>--%>

<%--  <sec:authorize access="isAuthenticated()">
    <s:url value="/resources/images" var="images_url" />
    <img src="${images_url}/my_avatar.jpg" align="middle"/>
	  <span><sec:authentication
              property="principal.username" /></span>
    <br/>
    <s:url value="/static/j_spring_security_logout"
           var="logout_url" />
    <a href="${logout_url}">Logout</a>
    <sec:authorize url="/admin">
      <s:url value="/admin" var="admin_url" />
      <br/><a href="${admin_url}">Admin</a>
    </sec:authorize>
  </sec:authorize>--%>
</div>