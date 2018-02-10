<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="locale/receipt_list.jspf" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:wrapper title="${all_receipts}">
    <h2><span>${all_receipts}</span></h2>
    <form name="receiptListForm" method="POST" action="${contextPath}/libraryDispatcher">
        <table class="table_countable wide_table">
            <thead>
            <tr>
                <th>№</th>
                <th>${status}</th>
                <th>${first_name}</th>
                <th>${surname}</th>
                <th>${email}</th>
                <th>${book}</th>
                <th>${order_quantity}</th>
                <th>${order_number}</th>
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
                    <td></td>
                    <td>${receipt.status.name}</td>
                    <td>${receipt.user.name}</td>
                    <td>${receipt.user.surname}</td>
                    <td>${receipt.user.email}</td>
                    <td>${receipt.book.title}</td>
                    <td>${receipt.quantity}</td>
                    <td>${receipt.id}</td>
                    <td>${receipt.term}</td>
                    <c:choose>
                        <c:when test="${receipt.status.id == 1}">
                            <td>
                                <form class="confirmed" name="applyReceipt" method="POST"
                                      action="${contextPath}/libraryDispatcher">
                                    <input type="hidden" name="command" value="book_transfer"/>
                                    <input type="hidden" name="id" value="${receipt.id}"/>
                                    <input type="hidden" name="status_id" value="2">
                                    <input type="hidden" name="book_id" value="${receipt.book.id}">
                                    <input type="hidden" name="quantity" value="${receipt.quantity}">
                                    <input type="hidden" name="amount" value="${receipt.book.amount}">
                                    <input class="submit_button small_button" type="submit" value="${accept}"/>
                                </form>
                            </td>
                            <td>
                                <form class="confirmed" name="rejectReceipt" method="POST"
                                      action="${contextPath}/libraryDispatcher">
                                    <input type="hidden" name="command" value="book_transfer"/>
                                    <input type="hidden" name="id" value="${receipt.id}"/>
                                    <input type="hidden" name="status_id" value="4">
                                    <input type="hidden" name="book_id" value="${receipt.book.id}">
                                    <input type="hidden" name="quantity" value="${receipt.quantity}">
                                    <input type="hidden" name="amount" value="${receipt.book.amount}">
                                    <input class="submit_button small_button" type="submit" value="${reject}"/>
                                </form>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:if test="${receipt.status.id == 2}">
                            <form class="confirmed" name="returnBook" method="POST"
                                  action="${contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="book_transfer"/>
                                <input type="hidden" name="id" value="${receipt.id}"/>
                                <input type="hidden" name="status_id" value="3">
                                <input type="hidden" name="book_id" value="${receipt.book.id}">
                                <input type="hidden" name="quantity" value="${receipt.quantity}">
                                <input type="hidden" name="amount" value="${receipt.book.amount}">
                                <input class="submit_button small_button" type="submit" value="${return_book}"/>
                            </form>
                        </c:if>
                    </td>
                    <td>
                        <form class="confirmed" name="deleteReceipt" method="POST"
                              action="${contextPath}/libraryDispatcher">
                            <input type="hidden" name="command" value="receipt_delete"/>
                            <input type="hidden" name="id" value="${receipt.id}"/>
                            <input class="submit_button small_button delete_button" type="submit" value="${delete}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:wrapper>



