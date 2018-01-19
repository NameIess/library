<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index jsp</title>
</head>
<body>

<h1>Hello from Java!</h1>

<h5>Счетчик времени от запуска приложения до нажатия кнопки</h5>

<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>

<form name="Simple" action="LibraryDispatcher" method="POST">
    <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
    <input type="submit" name="button" value="Посчитать время"/>
</form>

<jsp:forward page="WEB-INF/jsp/user_sign_in.jsp"/>
<%--Путь к контексту : ${ pageContext.request.contextPath } <br/>--%>
<%--Имя хоста : ${ header["host"] }<br/>--%>
<%--Тип и кодировка контента : ${pageContext.response.contentType}<br/>--%>
<%--Кодировка ответа : ${pageContext.response.characterEncoding}<br/>--%>
<%--ID сессии : ${pageContext.request.session.id}<br/>--%>
<%--Время создания сессии в мсек : ${pageContext.request.session.creationTime}<br/>--%>
<%--Время последнего доступа к сессии : ${pageContext.request.session.lastAccessedTime}<br/>--%>
<%--Имя сервлета : ${pageContext.servletConfig.servletName}--%>
</body>
</html>
