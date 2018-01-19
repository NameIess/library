<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.log_in" var="submit_button"/>
<fmt:message bundle="${loc}" key="local.sign_in" var="sign_in"/>

<t:wrapper title="${sign_in}">
    <h2><span>${sign_in}</span></h2>
    <form class="validated_form" name="loginForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
        <input type="hidden" name="command" value="user_sign_in"/>
        <div class="registration_group">
            <input type="text" class="verifiable" name="email" id="email" placeholder="${email}" />
            <strong class="error_message"></strong>
        </div>
        <div class="registration_group">
            <input type="password" class="verifiable" name="password" id="password" placeholder="${password}" />
            <strong class="error_message"></strong>
        </div>

        <input class="submit_button" type="submit" name="submit" value="${submit_button}" />
    </form>
</t:wrapper>
