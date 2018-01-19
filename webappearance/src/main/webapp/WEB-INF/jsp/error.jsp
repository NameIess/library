<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper title="error">
    <p>
        Request from ${pageContext.errorData.requestURI} is failed
        <br/>
        Servlet name: ${pageContext.errorData.servletName}
        <br/>
        Status code: ${pageContext.errorData.statusCode}
        <br/>
        Exception: ${pageContext.exception}
        <br/>

        Message from dao.exception: ${pageContext.exception.message}
    </p>
</t:wrapper>
