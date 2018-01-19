<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.registred_users" var="registred_users"/>
<fmt:message bundle="${loc}" key="local.id" var="entity_id"/>
<fmt:message bundle="${loc}" key="local.role_id" var="role_id"/>
<fmt:message bundle="${loc}" key="local.personnel_number" var="personnel_number"/>
<fmt:message bundle="${loc}" key="local.name" var="name"/>
<fmt:message bundle="${loc}" key="local.second_name" var="second_name"/>
<fmt:message bundle="${loc}" key="local.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.email" var="email"/>
<fmt:message bundle="${loc}" key="local.phone_number" var="phone_number"/>
<fmt:message bundle="${loc}" key="local.passport_series" var="passport_series"/>
<fmt:message bundle="${loc}" key="local.passport_number" var="passport_number"/>
<fmt:message bundle="${loc}" key="local.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.edit" var="edit"/>

<t:wrapper title="${registred_users}">
    <h2><span>${registred_users}</span></h2>
    <form name="userListForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>${role_id}</th>
                <th>${personnel_number}</th>
                <th>${name}</th>
                <th>${second_name}</th>
                <th>${surname}</th>
                <th>${email}</th>
                <th>${phone_number}</th>
                <th>${passport_series}</th>
                <th>${passport_number}</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td>${user.role.name}</td>
                    <td>${user.employeeNumber}</td>
                    <td>${user.name}</td>
                    <td>${user.secondName}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.passportSeries}</td>
                    <td>${user.passportNumber}</td>
                    <td>
                            <form name="updateUser" method="POST"
                                  action="${pageContext.request.contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="user_prepare_edit"/>
                                <input type="hidden" name="user_id" value="${user.id}"/>
                                <input class="submit_button" type="submit" value="${edit}"/>
                            </form>
                    </td>
                    <td>
                        <c:if test="${user.role.id > 1}">
                            <form name="deleteUser" method="POST"
                                  action="${pageContext.request.contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="user_delete"/>
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input class="submit_button" type="submit" value="${delete}"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:wrapper>
