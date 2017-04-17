<%--
  Created by IntelliJ IDEA.
  User: Artem
  Date: 17.04.2017
  Time: 3:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
  <h2>Sign in to Minimessages</h2>

  <spring:url var="authUrl"
              value="/static/j_spring_security_check" /><!--<co id="co_securityCheckPath"/>-->
  <form method="post" class="signin" action="${authUrl}">

    <fieldset>
      <table cellspacing="0">
        <tr>
          <th><label for="login">Login</label></th>
          <td><input id="login"
                     name="j_username"
                     type="text" />  <!--<co id="co_usernameField"/>-->
          </td>
        </tr>
        <tr>
          <th><label for="password">Password</label></th>
          <td><input id="password"
                     name="j_password"
                     type="password" /> <!--<co id="co_passwordField"/>-->
            <small><a href="/account/resend_password">Forgot?</a></small>
          </td>
        </tr>
<%--        <tr>
          <th></th>
          <td><input id="remember_me"
                     name="_spring_security_remember_me"
                     type="checkbox"/>
            <label for="remember_me"
                   class="inline">Remember me</label></td>
        </tr>--%>
        <tr>
          <th></th>
          <td><input name="commit" type="submit" value="Sign In" /></td>
        </tr>
      </table>
    </fieldset>
  </form>
</div>
