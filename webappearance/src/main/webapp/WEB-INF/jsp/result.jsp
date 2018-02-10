<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="locale/result.jspf" %>

<t:wrapper title="${report}">
    <h2><span>${report}</span></h2>

    <strong id="error_message" class="message error">${error_message}</strong>
    <strong id="success_message" class="message success">${success_message}</strong>

</t:wrapper>

