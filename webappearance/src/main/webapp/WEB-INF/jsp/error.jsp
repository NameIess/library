<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="locale/error.jspf" %>

<t:wrapper title="${error}">
    <h2><span>${error}</span></h2>

    <strong id="error_message" class="message">${error_message}</strong>
</t:wrapper>
