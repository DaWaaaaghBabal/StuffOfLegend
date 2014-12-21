<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The Stuff of Legend - Home</title>
</head>
<body>
	<jstl:forEach var="i" begin="0" end="7" step="1">
		<p>
			<jstl:out value="Hello, World ! ${i}" />
		</p>
	</jstl:forEach>
</body>
</html>