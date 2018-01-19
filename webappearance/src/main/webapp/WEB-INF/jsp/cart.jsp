<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.cart" var="cart"/>
<fmt:message bundle="${loc}" key="local.title" var="title"/>
<fmt:message bundle="${loc}" key="local.author" var="author"/>
<fmt:message bundle="${loc}" key="local.description" var="description"/>
<fmt:message bundle="${loc}" key="local.order_quantity" var="order_quantity"/>
<fmt:message bundle="${loc}" key="local.rent.subscribe" var="subscribe"/>
<fmt:message bundle="${loc}" key="local.usage_time" var="usage_time"/>
<fmt:message bundle="${loc}" key="local.order_now" var="order_now"/>

<t:wrapper title="${cart}">
    <h2><span>${cart}</span></h2>
    <div class="cart">
        <div class="book_data">
            <h3><c:out value="${requestScope.book.title}"/></h3>
            <p><c:out value="${requestScope.book.author}"/></p>
            <p><c:out value="${requestScope.book.yearOfPublishing} y"/></p>
            <p><c:out value="${requestScope.book.numberOfPages} pages"/></p>
            <p><c:out value="${requestScope.book.description}"/></p>
        </div>

        <form class="order_form" name="orderBookForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
            <input type="hidden" name="command" value="book_order"/>
            <input type="hidden" name="id" value="${requestScope.book.id}"/>
            <input type="hidden" name="available_amount" value="${requestScope.book.amount}"/>
            <input type="number" name="quantity" min="1" max=${requestScope.book.amount} step="1" title="${order_quantity}" placeholder="${order_quantity}">
            <input type="checkbox" name="is_subscription" id="subscription" value="true" title="${subscribe}" checked>
            <label for="subscription">${subscribe}</label>
            <input type="date" name="" id="date_pick">
            <div class="clearable-picker">
                <input name="" type="text" class="timepicker" id="time_pick">
            </div>
            <input class="submit_button" type="submit" value="${order_now}"/>
        </form>
    </div>
</t:wrapper>