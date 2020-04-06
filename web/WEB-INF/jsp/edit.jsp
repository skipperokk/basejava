<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <hr>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
            <dl>
                <dt>${type.title}</dt>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                        <dd><input type="text" name="${type}" size="50" value='<%=((TextSection)section).getContent()%>'></dd>
                    </c:when>
                    <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                        <dd><textarea name='${type}' cols=52 rows=3>
                            <%=String.join("\n", ((ListSection) section).getItems())%></textarea></dd>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="org" items='<%=((OrganizationSection)section).getOrganizations()%>'>
                            <dl>
                                <dt><i>Организация:</i></dt>
                                <dd><input type="text" name='${type}' size="50" value='${org.homePage.name}'></dd>
                            </dl>
                            <dl>
                                <dt><i>Cайт организации:</i></dt>
                                <dd><input type="text" name='${type}' size="50" value='${org.homePage.url}'></dd>
                            </dl>
                            <br>
                            <c:forEach var="pos" items='${org.positions}'>
                                <jsp:useBean id="pos" type="ru.javawebinar.basejava.model.Organization.Position"/>
                                <dl>
                                    <dt><i>Начало:</i></dt>
                                    <dd><input type="text" name="${type}" size="50" value="<%=pos.getStartDate()%>"></dd>
                                </dl>
                                <dl>
                                    <dt><i>Окончание:</i></dt>
                                    <dd><input type="text" name="${type}" size="50" value="<%=pos.getEndDate()%>"></dd>
                                </dl>
                                <dl>
                                    <dt><i>Позиция:</i></dt>
                                    <dd><input type="text" name="${type}" size="50" value="${pos.title}"></dd>
                                </dl>
                                <dl>
                                    <dt><i>Описание:</i></dt>
                                    <dd><input type="text" name="${type}" size="50" value="${pos.description}"></dd>
                                </dl>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </dl>
        </c:forEach>
        <br>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>