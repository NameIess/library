<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ include file="locale/user_sign_in.jspf" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:wrapper title="${sign_in}">
    <h2><span>${sign_in}</span></h2>

    <strong id="success_message" class="message success">${success_message}</strong>

    <form class="validated_form" name="loginForm" method="POST" action="${contextPath}/libraryDispatcher">
        <input type="hidden" name="command" value="user_sign_in"/>
        <div class="registration_group">
            <p>${mail}</p>
            <input type="text" class="verifiable" name="email" id="email" placeholder="${email_placeholder}" />
            <strong class="message fault">${authorization_error_message}</strong>
        </div>
        <div class="registration_group">
            <p>${password}</p>
            <input type="password" class="verifiable" name="password" id="password" placeholder="${password}" />
            <strong class="message fault">${authorization_error_message}</strong>
        </div>

        <input class="submit_button" type="submit" name="submit" value="${submit_button}" />
    </form>
</t:wrapper>