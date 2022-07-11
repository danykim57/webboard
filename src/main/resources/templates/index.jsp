<%@ page language="java">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://fonts.googleapis.com/earlyacess/notosanskr.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
    <div class="container">
        <table class = "table">
            <thead>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${fn:length(list) > 0 }">
                        <c:forEach items="${list} " var="bList">
                        <tr>
                            <th scope="row">${bList.IDX} </th>
                            <td>${bList.TITLE}</td>
                            <td>${bList.CREA_ID}</td>
                            <td>${bList.HIT_CNT }</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan = "5"> 조회된 결과가 없습니다.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>