<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="title" required="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="../jsp/locale/wrapper.jspf" %>
<c:set var="admin_role" value="1" />
<c:set var="librarian_role" value="2" />
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/wickedpicker.css">
    <title>
        ${title}
    </title>
</head>

<body>
<header>
    <h1><span>guardian of books</span></h1>
    <nav id="navmenu">
        <ul id="navigation" class="menu_list">
            <li>
                <a href="${pageContext.request.contextPath}/libraryDispatcher?command=library_home">${library}</a>
            </li>
            <c:if test="${sessionScope.user != null}">
                <li>
                    <a href="${pageContext.request.contextPath}/libraryDispatcher?command=user_receipt">${my_books}</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.role.id == admin_role}">
                <li>
                    <a href="${pageContext.request.contextPath}/libraryDispatcher?command=user_list">${users}</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.role.id <= librarian_role}">
                <li>
                    <a href="${pageContext.request.contextPath}/libraryDispatcher?command=receipt_list">${receipts}</a>
                </li>
            </c:if>
        </ul>
        <ul id="option" class="menu_list">
            <li>
                <form name="rejectReceipt" method="GET"
                      action="${pageContext.request.contextPath}/libraryDispatcher">
                    <input type="hidden" name="command" value="change_locale"/>
                    <select id="localization" name="locale" >
                        <option>${language}</option>
                        <option value="ru">RUS</option>
                        <option value="en">ENG</option>
                        <option value="de">GER</option>
                    </select>
                </form>
            </li>
            <c:if test="${sessionScope.user != null}">
                <li>
                    <a href="libraryDispatcher?command=user_sign_out">${logout}</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user == null}">
                <li>
                    <a href="${pageContext.request.contextPath}/libraryDispatcher?command=show_page&redirect=user_sign_in">${sign_in}</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/libraryDispatcher?command=show_page&redirect=user_registration">${registration}</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <hr>
</header>
<main id="body">
    <jsp:doBody/>
    <div class="foot_up"></div>
</main>
<footer id="footer">
    <article>
        <h4>${footer}</h4>
        <ul>
            <li>
                <a href="https://plus.google.com/u/1/105623136449404869270" id="googleplus"></a>
            </li>
        </ul>
    </article>
</footer>
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/wickedpicker.js"></script>
<script src="${contextPath}/resources/js/user_validator.js"></script>
<script src="${contextPath}/resources/js/event_handler.js"></script>
</body>
</html>