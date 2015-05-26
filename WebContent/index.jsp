<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="sigefirrhh.login.LoginSession" %>

<% 

	if ( !((LoginSession)session.getAttribute("loginSession")).isValid() ) {
		response.sendRedirect("/sigefirrhh/error.html");
	}

%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Hola mundo</title>
</head>
<body>
   
<select>
<option>Aprobar</option>
<option>Anular</option>
</select>

<embed src="reference.pdf" width="500" height="375"></embed>
	

</body>

</html>