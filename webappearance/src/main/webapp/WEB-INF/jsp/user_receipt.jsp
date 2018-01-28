<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="locale/user_receipt.jspf" %>

<t:wrapper title="${my_books}">
    <h2><span>${my_books}</span></h2>
    <form name="userListForm" method="POST" action="${pageContext.request.contextPath}/libraryDispatcher">
        <table class="table_countable">
            <thead>
            <tr>
                <th>№</th>
                <th>${author}</th>
                <th>${title}</th>
                <th>${year_of_publishing}</th>
                <th>${number_of_pages}</th>
                <th>${order_quantity}</th>
                <th>${order_status}</th>
                <th>${rental_time}</th>
                <th>${return_book}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.receipts}" var="receipt">
                <tr>
                    <td></td>
                    <td>${receipt.book.author}</td>
                    <td>${receipt.book.title}</td>
                    <td>${receipt.book.yearOfPublishing}</td>
                    <td>${receipt.book.numberOfPages}</td>
                    <td>${receipt.quantity}</td>
                    <td>${receipt.status.name}</td>
                    <td>${receipt.term}</td>
                    <td>
                        <c:if test="${receipt.status.id == 1}">
                            <form name="deleteReceipt" method="POST"
                                  action="${pageContext.request.contextPath}/libraryDispatcher">
                                <input type="hidden" name="command" value="receipt_delete"/>
                                <input type="hidden" name="id" value="${receipt.id}"/>
                                <input class="submit_button" type="submit" value="${delete_request}"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</t:wrapper>
