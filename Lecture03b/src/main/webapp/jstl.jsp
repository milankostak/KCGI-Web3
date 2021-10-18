<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL - Hello World</title>
</head>
<body>
<br>
<c:out value="${'JSTL c:out'}"/>

<br>
<c:if test="${4 % 2 == 0}">
    <p>Number 4 is divisible by 2 without a reminder.</p>
</c:if>

<h2>Different Formats of the Date</h2>
<c:set var="date" value="<%=new java.util.Date()%>"/>
<p>
    Formatted Time:
    <fmt:formatDate type="time" value="${date}"/>
</p>
<p>
    Formatted Date:
    <fmt:formatDate type="date" value="${date}"/>
</p>
<p>
    Formatted Date and Time:
    <fmt:formatDate type="both" value="${date}"/>
</p>
<p>
    Formatted Date and Time in short style:
    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${date}"/>
</p>
<p>
    Formatted Date and Time in medium style:
    <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${date}"/>
</p>
<p>
    Formatted Date and Time in long style:
    <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${date}"/>
</p>
<p>
    Formatted Date and Time in long style with different time zone:
    <fmt:formatDate type="both" dateStyle="long" timeStyle="long" timeZone="GMT" value="${date}"/>
</p>

</body>
</html>


</body>
</html>