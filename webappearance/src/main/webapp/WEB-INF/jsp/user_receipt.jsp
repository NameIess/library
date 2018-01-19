<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.my_books" var="my_books"/>
<fmt:message bundle="${loc}" key="local.title" var="title"/>
<fmt:message bundle="${loc}" key="local.author" var="author"/>
<fmt:message bundle="${loc}" key="local.year_of_publishing" var="year_of_publishing"/>
<fmt:message bundle="${loc}" key="local.order_quantity" var="order_quantity"/>
<fmt:message bundle="${loc}" key="local.number_of_pages" var="number_of_pages"/>
<fmt:message bundle="${loc}" key="local.rent.subscribe" var="subscribe"/>
<fmt:message bundle="${loc}" key="local.order_status" var="order_status"/>
<fmt:message bundle="${loc}" key="local.rental_time" var="rental_time"/>
<fmt:message bundle="${loc}" key="local.delete_request" var="delete_request"/>
<fmt:message bundle="${loc}" key="local.return_book" var="return_book"/>

<t:wrapper title="${my_books}">
    <h2><span>${my_books}</span></h2>
    <form name="userListForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
        <table class="table_list">
            <thead>
            <tr>
                <th>${author}</th>
                <th>${title}</th>
                <th>${year_of_publishing}</th>
                <th>${number_of_pages}</th>
                <th>${order_quantity}</th>
                <th>${order_status}</th>
                <th>${rental_time}</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.receipts}" var="receipt">
                <tr>
                    <td>${receipt.book.author}</td>
                    <td>${receipt.book.title}</td>
                    <td>${receipt.book.yearOfPublishing}</td>
                    <td>${receipt.book.numberOfPages}</td>
                    <td>${receipt.quantity}</td>
                    <td>${receipt.status.name}</td>
                    <td>${receipt.term}</td>
                    <td>
                        <c:choose>
                            <c:when test="${receipt.status.id == 1}">
                                <form name="deleteReceipt" method="POST"
                                      action="${pageContext.request.contextPath}/libraryDispatcher">
                                    <input type="hidden" name="command" value="receipt_delete"/>
                                    <input type="hidden" name="id" value="${receipt.id}"/>
                                    <input class="submit_button" type="submit" value="${delete_request}"/>
                                </form>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:wrapper>
