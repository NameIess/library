<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="time" uri="/WEB-INF/tld/dayTime.tld" %>
<%@ include file="locale/cart.jspf" %>

<t:wrapper title="${cart}">
    <h2><span>${cart}</span></h2>
    <div class="cart">
        <div class="book_data">
            <p>${title} : <c:out value="${requestScope.book.title}"/></>
            <p>${author} : <c:out value="${requestScope.book.author}"/></p>
            <p>${year_of_publishing} : <c:out value="${requestScope.book.yearOfPublishing}"/></p>
            <p>${number_of_pages} : <c:out value="${requestScope.book.numberOfPages}"/></p>
            <p>${description} : <c:out value="${requestScope.book.description}"/></p>
            <p>${in_stock} : <c:out value="${requestScope.book.amount}"/></p>
        </div>

        <div class="order_data" id="order_data">
            <form class="order_form" name="orderBookForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
                <input type="hidden" name="command" value="book_order"/>
                <input type="hidden" name="id" value="${requestScope.book.id}"/>
                <input type="hidden" name="available_amount" value="${requestScope.book.amount}"/>
                <div class="subscription">
                    <input type="checkbox" name="is_subscribtion" id="subscription" value="true" title="${subscribe}" checked>
                    <label for="subscription">${subscribe}</label>
                </div>
                <p>${current_time} <b><time:info-time locale="${sessionScope.locale}"/></b></p>
                <input type="date" name="" id="date_pick">
                <input class="timepicker" name="" type="text" id="time_pick">
                <strong id="cart_message_date" class="message cart_strong"></strong>
                <input type="number" name="quantity" min="1" max=${requestScope.book.amount} step="1" title="${order_quantity}" placeholder="${order_quantity}">
                <strong id="cart_message" class="message cart_strong"></strong>

                <input class="submit_button bordered_button" type="submit" value="${order_now}"/>
            </form>
        </div>
    </div>

</t:wrapper>
