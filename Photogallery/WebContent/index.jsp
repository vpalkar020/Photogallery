<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="FilesHandler?action=filesUpload" method="post" enctype="multipart/form-data">
<input type="file" name ="files" multiple>
<input type="submit" value="submit">
</form>
<a href="${pageContext.request.contextPath}/FilesHandler?action=listingImages">View Images </a>
</body>
</html>