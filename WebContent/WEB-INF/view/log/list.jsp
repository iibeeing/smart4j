<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志查看</title>
</head>
<body>
	<h1>日志列表</h1>
	<table border="1px" width="100%" style="text-align: center;">
		<tr>
			<th>序号</th>
			<th>语句</th>
			<th>执行结果</th>
			<th>操作人</th>
			<th>操作时间</th>
		</tr>
		<c:forEach var="log" items="${list}" varStatus="status">
			<tr align="center">
				<td>${status.index + 1}</td>  
				<td>${log.statement }</td>
				<c:if test="${log.result == true}">
				<td>成功</td>
				</c:if>
				<c:if test="${log.result == false}">
				<td>失败</td>
				</c:if>
				<td>${log.operator }</td>
				<td>${log.operatetime }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>