<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="locale/user_registration.jspf" %>

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
                <input type="text" value="${requestScope.edit_user.employeeNumber}" name="employee_number" title="${personnel_number}" id="employee_number" placeholder="${personnel_number}" autofocus>
                <strong class="message"></strong>
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
            <input type="text" class="verifiable" name="name" title="${first_name}" value="${requestScope.edit_user.name}" id="name" placeholder="${first_name}" autofocus>
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <input type="text" class="verifiable" name="second_name" title="${second_name}" value="${requestScope.edit_user.secondName}" id="second_name" placeholder="${second_name}">
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <input type="text" class="verifiable" name="surname" title="${surname}" value="${requestScope.edit_user.surname}" id="surname" placeholder="${surname}">
            <strong class="message"></strong>
        </div>
        <c:if test="${isNew == 1}">
            <div class="registration_group">
                <input type="text" class="verifiable" name="email" title="${email_address}" value="${requestScope.edit_user.email}" id="email" placeholder="${email_address}">
                <strong class="message">${duplicated_email}</strong>
            </div>
            <div class="registration_group">
                <input type="password" class="verifiable" name="password" title="${password}" id="password" placeholder="${password}">
                <strong class="message"></strong>
            </div>
            <div class="registration_group">
                <input type="password" class="verifiable" name="confirm_password" title="${confirm_password}" id="confirm_password" placeholder="${confirm_password}">
                <strong class="message"></strong>
            </div>
        </c:if>
        <div class="registration_group">
            <input type="text" name="phone_number" title="${phone_number}" value="${requestScope.edit_user.phoneNumber}" id="phone_number" placeholder="${phone_number}">
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <input type="text" class="verifiable not_null" name="passport_series" title="${passport_series}" value="${requestScope.edit_user.passportSeries}" id="passport_series" placeholder="${passport_series}">
            <strong class="message"></strong>
        </div>

        <div class="registration_group">
            <input type="text" class="verifiable not_null" name="passport_number" title="${passport_number}" value="${requestScope.edit_user.passportNumber}" id="passport_number" placeholder="${passport_number}">
            <strong class="message"></strong>
        </div>

        <input class="submit_button" type="submit" title="${submit}" value="${submit}">
    </form>
</t:wrapper>
