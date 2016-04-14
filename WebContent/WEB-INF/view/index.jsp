<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%> --%>
<%-- <%@ taglib prefix="security" uri="/security"%> --%>
<c:set var="BASE" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<h1>首页</h1>
	<security:guest>
		<p>身份：游客</p>
		<a href="${BASE} /login">登录</a>
	</security:guest>
	<security:user>
		<p>
			身份：
			<security:princepal />
		</p>
		<ul>
			<li><a href="${BASE}/customer">客户管理</a></li>
		</ul>
		<a href="c:url value=" /logout"/>">注销</a>
	</security:user>
</body>
</html>