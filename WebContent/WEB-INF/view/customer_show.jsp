<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</html>