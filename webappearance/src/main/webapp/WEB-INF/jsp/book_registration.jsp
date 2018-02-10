<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="locale/book_registration.jspf" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:wrapper title="${registration}">
    <h2><span>${registration}</span></h2>

    <form class="validated_form" name="book_registration_form" method="POST" action="${contextPath}/libraryDispatcher">
        <input type="hidden" name="command" value="book_edit"/>

        <input type="hidden" value="${requestScope.edit_book.id}" name="book_id">

        <div class="registration_group">
            <p>${title}</p>
            <input type="text" class="verifiable book_data" name="title" title="${title}" value="${requestScope.edit_book.title}" id="title" placeholder="${title}" autofocus>
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <p>${author}</p>
            <input type="text" class="verifiable book_data" name="author" title="${author}" value="${requestScope.edit_book.author}" id="author" placeholder="${author}">
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <p>${year_of_publishing}</p>
            <input type="text" class="verifiable book_number" name="year_of_publishing" title="${year_of_publishing}" value="${requestScope.edit_book.yearOfPublishing}" id="year_of_publishing" placeholder="${year_of_publishing}">
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <p>${number_of_pages}</p>
            <input type="text" class="verifiable book_number" name="number_of_pages" title="${number_of_pages}" value="${requestScope.edit_book.numberOfPages}" id="number_of_pages" placeholder="${number_of_pages}">
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <p>${in_stock}</p>
            <input type="text" class="verifiable book_number" name="amount" title="${in_stock}" value="${requestScope.edit_book.amount}" id="amount" placeholder="${in_stock}">
            <strong class="message"></strong>
        </div>
        <div class="registration_group">
            <p>${description}</p>
            <textarea type="text" class="verifiable" name="description" title="${description}" id="description" placeholder="${description}">${requestScope.edit_book.description}</textarea>
            <strong class="message"></strong>
        </div>

        <input class="submit_button" type="submit" title="${submit}" value="${submit}">
    </form>
</t:wrapper>
