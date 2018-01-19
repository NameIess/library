<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.library" var="library"/>
<fmt:message bundle="${loc}" key="local.title" var="title"/>
<fmt:message bundle="${loc}" key="local.author" var="author"/>
<fmt:message bundle="${loc}" key="local.year_of_publishing" var="year_of_publishing"/>
<fmt:message bundle="${loc}" key="local.in_stock" var="amount"/>
<fmt:message bundle="${loc}" key="local.number_of_pages" var="number_of_pages"/>
<fmt:message bundle="${loc}" key="local.more_info" var="more"/>


<t:wrapper title="${library}">
    <h2><span>${library}</span></h2>

    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>${title}</th>
                <th>${author}</th>
                <th>${amount}</th>
                <th>${more}</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${requestScope.books}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.amount}</td>
                    <td>
                        <form name="addToCartForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
                            <input type="hidden" name="command" value="add_book_to_cart"/>
                            <input type="hidden" name="id" value="${book.id}"/>
                            <input class="submit_button" type="submit" value="${more}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:wrapper>