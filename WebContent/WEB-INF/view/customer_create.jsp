<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理 - 创建客户</title>
</head>
<!-- <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script> -->
<body>
	<h1>创建客户界面</h1>
	<%-- <form action="${BASE }/customer_create" method="POST"id="customer_form" enctype="multipart/form-data"> --%>
	<!-- <form id="customer_form" method="POST" enctype="multipart/form-data"> -->
	<form id="customer_form" method="POST" enctype="multipart/form-data">
		<table>
			<tr>
				<td>客户名称：</td>
				<td><input type="text" name="customer.name"
					value="${customer.name }" /></td>
			</tr>
			<tr>
				<td>联系方式：</td>
				<td><input type="text" name="customer.contact"
					value="${customer.contact }" /></td>
			</tr>
			<tr>
				<td>电话：</td>
				<td><input type="text" name="customer.telephone"
					value="${customer.telephone }" /></td>
			</tr>
			<tr>
				<td>电子邮件：</td>
				<td><input type="text" name="customer.email"
					value="${customer.email }" /></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input type="text" name="customer.remark"
					value="${customer.remark}" /></td>
			</tr>
			<tr>
				<td>照片：</td>
				<td><input type="file" name="customer.photo"  value="${customer.photo}" /></td>
			</tr>
		</table>
		<button type="submit" id="submit">提交</button>
	</form>
</body>
<script type="text/javascript" src="${BASE}/js/query.js"></script>
<script type="text/javascript" src="${BASE}/js/query.form.js"></script>
<script type="text/javascript">
	/* $("#submit").click(function() {
		$.ajax({
			type : "POST",
			url : "${BASE }/customer_create",
			success : function(msg) {
				alert("Data Saved: " + msg);
			}
		});
	}); */

	$("#submit").click(function() {
		$("#customer_form").ajaxForm({
			type : "POST",
			url : "${BASE}/customer_create",
			success : function(data) {
				if (data) {
					location.href = "${BASE}/customer";
				}
			}
		});
	});
</script>
</html>