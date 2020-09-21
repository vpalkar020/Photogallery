<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="org.studyeasy.hibernate.entity.Files" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Images</title>
</head>
<body>
<%!Files file;String path; %>
<%
file=(Files)request.getAttribute("file");
path=(String)request.getAttribute("path");
%>
FileName:<%=file.getFilename() %>
<%
if(file.getCaption()!=null){
	out.print("File Caption: "+file.getCaption());
}
%>
<%
if(file.getLabel()!=null){
	out.print("File Label: "+file.getLabel());
}
%>
<hr/>
</body>
</html>