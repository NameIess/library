<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="locale/user_list.jspf" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:wrapper title="${registred_users}">
    <h2><span>${registred_users}</span></h2>
    <form name="userListForm" method="POST" action="${contextPath}/libraryDispatcher">
        <table class="table_countable wide_table">
            <thead>
            <tr>
                <th>№</th>
                <th>${role_id}</th>
                <th>${personnel_number}</th>
                <th>${name}</th>
                <th>${second_name}</th>
                <th>${surname}</th>
                <th>${mail}</th>
                <th>${phone_number}</th>
                <th>${passport_series}</th>
                <th>${passport_number}</th>
                <th>${edit}</th>
                <th>${delete}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="user">
                <tr>
                    <td></td>
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
                              action="${contextPath}/libraryDispatcher">
                            <input type="hidden" name="command" value="user_prepare_edit"/>
                            <input type="hidden" name="user_id" value="${user.id}"/>
                            <input class="submit_button small_button" type="submit" value="${edit}"/>
                        </form>
                    </td>
                    <td>
                        <c:if test="${user.role.id > 1}">
                            <form name="deleteUser" method="POST"
                                  action="${contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="user_delete"/>
                                <input type="hidden" name="id" value="${user.id}"/>
                                <input class="submit_button small_button delete_button" type="submit" value="${delete}"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:wrapper>
