<%--
  Created by IntelliJ IDEA.
  User: citish02
  Date: 2021/1/20
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath}/admin">
    <div class="styled-input" style="margin-top: 10px">
        <a style="color: #117a8b">上传TP字典</a>
        <input type="file" placeholder="待解析文件" name="image" accept=".xlsx, .csv"required="">
    </div>
    <button type="submit" class="btn subscrib-btnn">提交</button>
</form>
<form method="post" action="${pageContext.request.contextPath}/TP">
    <div>
        <button type="submit"  style="color: coral" class="btn subscrib-btnn">字典下载</button>
    </div>
</form>

</body>
</html>
