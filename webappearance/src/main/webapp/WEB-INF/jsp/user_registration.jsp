<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.second_name" var="second_name"/>
<fmt:message bundle="${loc}" key="local.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.password" var="password"/>
<fmt:message bundle="${loc}" key="local.confirm_password" var="confirm_password"/>
<fmt:message bundle="${loc}" key="local.phone_number" var="phone_number"/>
<fmt:message bundle="${loc}" key="local.passport_series" var="passport_series"/>
<fmt:message bundle="${loc}" key="local.passport_number" var="passport_number"/>
<fmt:message bundle="${loc}" key="local.submit" var="submit"/>
<fmt:message bundle="${loc}" key="local.edit_user" var="edit_user"/>
<fmt:message bundle="${loc}" key="local.personnel_number" var="personnel_number"/>
<fmt:message bundle="${loc}" key="locale.user_role" var="user_role"/>
<fmt:message bundle="${loc}" key="local.admin" var="admin"/>
<fmt:message bundle="${loc}" key="local.librarian" var="librarian"/>
<fmt:message bundle="${loc}" key="local.reader" var="reader"/>


<t:wrapper title="${registration}">
    <c:choose>
        <c:when test="${empty requestScope.edit_user}">
            <h2><span>${registration}</span></h2>
            <c:set var="isNew" value="1" />
            <c:set var="command" value="user_registration"/>
        </c:when>
        <c:otherwise>
            <h2><span>${edit_user}</span></h2>
            <c:set var="isNew" value="0" />
            <c:set var="command" value="user_edit"/>
        </c:otherwise>
    </c:choose>
    <form class="validated_form" name="registration_form" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
        <input type="hidden" name="command" value="${command}"/>
        <c:if test="${isNew == 0}">
            <input type="hidden" value="${requestScope.edit_user.id}" name="user_id">

            <div class="registration_group">
                <input type="text" value="${requestScope.edit_user.employeeNumber}" name="employee_number" title="employee_number" id="employee_number" placeholder="${personnel_number}" autofocus>
                <strong class="error_message"></strong>
            </div>

            <div class="registration_group">
                <div class="select">
                    <select class="wide_select" id="role_select" name="role_id">
                        <option disabled>${user_role}</option>
                        <c:forEach items="${requestScope.roles}" var="role">
                            <option value="${role.id}" ${role.id == requestScope.edit_user.role.id ? "selected" : ""}>${role.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </c:if>

        <div class="registration_group">
            <input type="text" name="name" title="name" value="${requestScope.edit_user.name}" id="name" placeholder="${first_name}" autofocus>
            <strong class="error_message"></strong>
        </div>
        <div class="registration_group">
            <input type="text" name="second_name" value="${requestScope.edit_user.secondName}" id="second_name" placeholder="${second_name}">
            <strong class="error_message"></strong>
        </div>
        <div class="registration_group">
            <input type="text" name="surname" value="${requestScope.edit_user.surname}" id="surname" placeholder="${surname}">
            <strong class="error_message"></strong>
        </div>
        <c:if test="${isNew == 1}">
            <div class="registration_group">
                <input type="text" class="verifiable" name="email" value="${requestScope.edit_user.email}" id="email" placeholder="${email}">
                <strong class="error_message"></strong>
            </div>
            <div class="registration_group">
                <input type="password" class="verifiable" name="password" id="password" placeholder="${password}">
                <strong class="error_message"></strong>
            </div>
            <div class="registration_group">
                <input type="password" class="verifiable" name="confirm_password" id="confirm_password" placeholder="${confirm_password}">
                <strong class="error_message"></strong>
            </div>
        </c:if>
        <div class="registration_group">
            <input type="text" name="phone_number" value="${requestScope.edit_user.phoneNumber}" id="phone_number" placeholder="${phone_number}">
            <strong class="error_message"></strong>
        </div>
        <div class="registration_group">
            <input type="text" name="passport_series" value="${requestScope.edit_user.passportSeries}" id="passport_series" placeholder="${passport_series}">
            <strong class="error_message"></strong>
        </div>

        <div class="registration_group">
            <input type="text" name="passport_number" value="${requestScope.edit_user.passportNumber}" id="passport_number" placeholder="${passport_number}">
            <strong class="error_message"></strong>
        </div>

        <input class="submit_button" type="submit" value="${submit}">
    </form>
</t:wrapper>
