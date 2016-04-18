<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑 ${customer.name } 的信息</title>
</head>
<body>
<table>
<tr><td>姓名：</td><td>${customer.name }</td></tr>
<tr><td>联系方式：</td><td>${customer.contact }</td></tr>
<tr><td>电话：</td><td>${customer.telephone }</td></tr>
<tr><td>电子邮件：</td><td>${customer.email }</td></tr>
<tr><td>备注：</td><td>${customer.remark }</td></tr>
</table>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑 ${customer.name } 的信息</title>
</head>
<body>
<%-- <table>
<tr><td>姓名：</td><td>${customer.name }</td></tr>
<tr><td>联系方式：</td><td>${customer.contact }</td></tr>
<tr><td>电话：</td><td>${customer.telephone }</td></tr>
<tr><td>电子邮件：</td><td>${customer.email }</td></tr>
<tr><td>备注：</td><td>${customer.remark }</td></tr>
</table> --%>
	<h1>编辑</h1>
	<form action="${BASE }/customer_edit" method="POST" id="form">
	<input type="hidden" name="id" value="${customer.id }"/>
		<table>
			<tr>
				<td>客户名称：</td>
				<td><input type="text" name="customer.name" value="${customer.name }"/></td>
			</tr>
			<tr>
				<td>联系方式：</td>
				<td><input type="text" name="customer.contact" value="${customer.contact }"/></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" name="customer.telephone" value="${customer.telephone }"/></td>
			</tr>
			<tr>
				<td>电子邮件：</td>
				<td><input type="text" name="customer.email" value="${customer.email }"/></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input type="text" name="customer.remark" value="${customer.remark}"/></td>
			</tr>
		</table>
		<input type="submit" value="提交">
	</form>
</body>
</html>