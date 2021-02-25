<%--
  Created by IntelliJ IDEA.
  User: citish02
  Date: 2021/1/20
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>上传待解析文件</title>
  </head>
  <body>
  <form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/user">
    <div class="styled-input" style="margin-top: 10px">
      <a style="color: #117a8b">上传待解析文件</a>
      <input type="file" placeholder="待解析文件" name="image" accept=".xlsx, .csv"required="">
    </div>
    <button type="submit" class="btn subscrib-btnn">提交</button>
  </form>
  <c:forEach items ="${warninglist}" var ="warning">
    <a>warning</a>
    <br>
  </c:forEach>
  </body>
</html>
