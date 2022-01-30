<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
pageContext.setAttribute("result", "hello");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <b>${result }</b>입니다.<br>
 <b>${requestScope.result }</b>입니다.<br>
 ${names[0] } <br>
 ${names[1] } <br>
 ${notice.title }<br>
 ${notice.id }<br>
 ${empty param.n? '값이 비어 있습니다.' : '값이 존재합니다.'} <br>
 ${param.n/2} <br>
 ${header.Host } <br>
</body>
</html>