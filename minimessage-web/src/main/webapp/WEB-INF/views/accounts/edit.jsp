<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
    <h2> Create Account</h2>

    <%--<sf:form method="post" modelAttribute="account" action="accounts/addAccount" enctype="multipart/form-data">--%>
<%--
    <form:form method="post" commandName="account" action="accounts/addAccount" enctype="multipart/form-data">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><form:label path="username">login</form:label></th>
                        &lt;%&ndash;This field binding with input object field  &ndash;%&gt;
                    &lt;%&ndash;<td><sf:input path="username" size="25" maxlength="25"/>&ndash;%&gt;
                    <td><form:input path="username"/>
                        <small id="username_msg">login must be without spaces</small>
                        <form:errors path="username" delimiter=" / " cssClass="error" />
                    </td>
                </tr>
                 <tr>
                    <th><form:label path="password">password</form:label></th>
                         &lt;%&ndash;This field binding with input object field  &ndash;%&gt;
                    &lt;%&ndash;<td><sf:password path="password" size="25" maxlength="25" showPassword="true"/>&ndash;%&gt;
                    <td><form:password path="password"/>
                        <small id="username_msg"> minimum 6, maximum 25 characters</small>
                        <form:errors path="password" delimiter=" / " cssClass="error" />
                        &lt;%&ndash;<sf:errors path="*" delimiter=" / " cssClass="error" />  &lt;%&ndash;Display errors at the top of the screen  &ndash;%&gt;&ndash;%&gt;
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit"
                               value="Create account" /></td>
                </tr>
            </table>
        </fieldset>
    </form:form>
--%>


    <%--<form:form method="POST" commandName="account" enctype="multipart/form-data">--%>
    <form:form method="POST" commandName="account">
        <table>
            <tr>
                <td>Login :</td>
                <td><form:input path="username" maxlength="25" /></td>
                <td><form:errors path="username" cssClass="error" /></td>
            </tr>
            <tr>
                <td>password :</td>
                <td><form:password path="password" maxlength="25" /></td>
                <td><form:errors path="password" cssClass="error" /></td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" /></td>
            </tr>
        </table>
    </form:form>
</div>