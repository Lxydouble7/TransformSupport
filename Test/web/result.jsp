<%--
  Created by IntelliJ IDEA.
  User: citish02
  Date: 2021/2/4
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>解析结果</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/download">
    <div>
        <button type="submit"  style="color: coral" class="btn subscrib-btnn">下载</button>
    </div>
</form>
<c:forEach items ="${warninglist}" var ="warning">
    <span style="color:red">${warning}</span>
    <br>
</c:forEach>

<form method="post" action="${pageContext.request.contextPath}/download">
    <div>
        <button type="submit"  style="color: coral" class="btn subscrib-btnn">下载</button>
    </div>
</form>

</body>
</html>
