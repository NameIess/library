<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.ordered_books" var="ordered_books"/>
<fmt:message bundle="${loc}" key="local.receipts" var="all_receipts"/>
<fmt:message bundle="${loc}" key="local.id" var="id"/>
<fmt:message bundle="${loc}" key="local.order_status" var="status"/>
<fmt:message bundle="${loc}" key="local.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.ordered_books" var="book"/>
<fmt:message bundle="${loc}" key="local.order_quantity" var="order_quantity"/>
<fmt:message bundle="${loc}" key="local.rental_time" var="rental_time"/>
<fmt:message bundle="${loc}" key="local.accept" var="accept"/>
<fmt:message bundle="${loc}" key="local.reject" var="reject"/>
<fmt:message bundle="${loc}" key="local.return_book" var="return_book"/>
<fmt:message bundle="${loc}" key="local.delete" var="delete"/>

<t:wrapper title="${all_receipts}">
    <h2><span>${all_receipts}</span></h2>
    <form name="receiptListForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>${status}</th>
                <th>${first_name}</th>
                <th>${surname}</th>
                <th>${book}</th>
                <th>${order_quantity}</th>
                <th>${rental_time}</th>
                <th>${accept}</th>
                <th>${reject}</th>
                <th>${return_book}</th>
                <th>${delete}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.receipts}" var="receipt">
                <tr>
                    <td>${receipt.status.name}</td>
                    <td>${receipt.user.name}</td>
                    <td>${receipt.user.surname}</td>
                    <td>${receipt.book.title}</td>
                    <td>${receipt.quantity}</td>
                    <td>${receipt.term}</td>
                    <td>
                        <c:if test="${receipt.status.id == 1}">
                            <form class="confirmed" name="applyReceipt" method="POST"
                                  action="${pageContext.request.contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="book_transfer"/>
                                <input type="hidden" name="id" value="${receipt.id}"/>
                                <input type="hidden" name="status_id" value="2">
                                <input type="hidden" name="book_id" value="${receipt.book.id}">
                                <input type="hidden" name="quantity" value="${receipt.quantity}">
                                <input type="hidden" name="amount" value="${receipt.book.amount}">
                                <input class="submit_button" type="submit" value="${accept}"/>
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${receipt.status.id == 1}">
                            <form class="confirmed" name="rejectReceipt" method="POST"
                                  action="${pageContext.request.contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="book_transfer"/>
                                <input type="hidden" name="id" value="${receipt.id}"/>
                                <input type="hidden" name="status_id" value="4">
                                <input type="hidden" name="book_id" value="${receipt.book.id}">
                                <input type="hidden" name="quantity" value="${receipt.quantity}">
                                <input type="hidden" name="amount" value="${receipt.book.amount}">
                                <input class="submit_button" type="submit" value="${reject}"/>
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${receipt.status.id == 2}">
                            <form class="confirmed" name="returnBook" method="POST"
                                  action="${pageContext.request.contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="book_transfer"/>
                                <input type="hidden" name="id" value="${receipt.id}"/>
                                <input type="hidden" name="status_id" value="3">
                                <input type="hidden" name="book_id" value="${receipt.book.id}">
                                <input type="hidden" name="quantity" value="${receipt.quantity}">
                                <input type="hidden" name="amount" value="${receipt.book.amount}">
                                <input class="submit_button" type="submit" value="${return_book}"/>
                            </form>
                        </c:if>
                    </td>

                    <td>
                        <form class="confirmed" name="deleteReceipt" method="POST"
                              action="${pageContext.request.contextPath}/libraryDispatcher">
                            <input type="hidden" name="command" value="receipt_delete"/>
                            <input type="hidden" name="id" value="${receipt.id}"/>
                            <input class="submit_button" type="submit" value="${delete}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:wrapper>



