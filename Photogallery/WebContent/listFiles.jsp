<%@page import="java.util.List"%>
<%@page import="org.studyeasy.hibernate.entity.Files"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image List</title>
</head>
<body>
<%! String form;int fileId;String filename;%>
<h3>Listing Images</h3>
<table border="1">
<tr>
<th>Preview</th>
<th>Available Information</th>
<th>Update Information</th>
<th>Available Action</th>
</tr>
<%
	String path = (String)request.getAttribute("path");
	List<Files> files=(List<Files>)request.getAttribute("files");
	for(Files file:files){	
	out.print("<tr><td><img src="+path+file.getFilename()+" height='200' width='300'></td>");
	out.print("<td><ul>"+
			  "<li>File Id: "+file.getId()+"</li>"+
			  "<li>File Name: "+file.getFilename()+"</li>"+
			  "<li>File Caption: "+file.getCaption()+"</li>"+
			  "<li>File label: "+file.getLabel()+"</li>"+
			  "</ul></td>");
	fileId=file.getId();
	filename=file.getFilename();
	String form="<form action='FilesHandler'method='post'>"+
			"Label: <input type='text' name='label'/>"+"</br></br>"+
			"Caption: <input type='text' name='caption'/>"+"</br></br>"+
			"<input type='hidden' name='filename' value='"+filename+"'/>"+
			"<input type='hidden' name='fileId' value='"+fileId+"'/>"+
			"<input type='hidden' name='action' value='updateInformation'/>"+
			"<input type='submit' value='update'/>"+
			"</form>";		  
			out.print("<td>"+form+"</td>");
			out.print("<td><ul><li><a href='"+request.getContextPath()+"/FilesHandler?action=viewImage&fileId="+
			file.getId()+"'>View Image</a></li>");
			out.print("<li><a href='"+request.getContextPath()+"/FilesHandler?action=deleteImage&fileId="+
			file.getId()+"' onclick=\"if(!confirm('Are you Sure Want To Delete the User?')) return false\">Delete Image</a></li></ul></td></tr>");
	}
	%>
</table>
</body>
</html>