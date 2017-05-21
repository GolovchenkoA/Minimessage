<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div>
    <h2> Create Account.</h2>


     <%--<form:form method="POST" commandName="account" enctype="multipart/form-data">--%> <%-- enctype="multipart/form-data"  приводит к тому что объект не принимается сервлетом  --%>
     <%--<form:form method="POST" commandName="account" >--%>
     <form:form method="POST" modelAttribute="account" >
        <%--<form:errors path="*" cssClass="errorblock" element="div" />--%><%--Show error messages at the top   --%>
        <table>
            <tr>
                <td>Login</td>
                <td><form:input path="username" maxlength="25" title="example: JackieChan"/></td>
                <td>
                    <%--<form:errors path="username" cssClass="error" /></td>--%>
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td><form:password path="password" maxlength="25" showPassword="true" title="example: P@$$w0Rd"/></td>
                <td>
                    <small id="username_msg"> minimum 6, maximum 25 characters</small>
                    <%--<form:errors path="password" cssClass="error" />--%>
                </td>
            </tr>
            <tr>
                <td colspan="3"><input type="submit" /></td>
            </tr>
        </table>
         <form:errors path="*" cssClass="error" />
    </form:form>






    <%--<sf:form method="post" modelAttribute="account" action="accounts/addAccount" enctype="multipart/form-data">--%>
<%--    <form:form method="post" commandName="account">
        <fieldset>
            <table cellspacing="0">
                <tr>
                    <th><form:label path="username">login</form:label></th>
                        &lt;%&ndash;This field binding with input object field  &ndash;%&gt;
                    &lt;%&ndash;<td><sf:input path="username" size="25" maxlength="25"/>&ndash;%&gt;
                    <td><form:input path="username"/>
                        <small id="username_msg">login must be without spaces</small>
                        &lt;%&ndash;<form:errors path="username" delimiter=" / " cssClass="error" />&ndash;%&gt;
                    </td>
                </tr>
                 <tr>
                    <th><form:label path="password">password</form:label></th>
                         &lt;%&ndash;This field binding with input object field  &ndash;%&gt;
                    &lt;%&ndash;<td><sf:password path="password" size="25" maxlength="25" showPassword="true"/>&ndash;%&gt;
                    <td><form:password path="password"/>
                        <small id="username_msg"> minimum 6, maximum 25 characters</small>
                        &lt;%&ndash;<form:errors path="password" delimiter=" / " cssClass="error" />&ndash;%&gt;
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td><input name="commit" type="submit"
                               value="Create account" /></td>
                </tr>
            </table>
                <form:errors path="*" cssClass="error" element="div" />
        </fieldset>
    </form:form>--%>



</div>