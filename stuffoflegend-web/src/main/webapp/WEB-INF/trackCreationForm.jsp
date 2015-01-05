<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stuff of Legend - Track creation</title>
<script src="/stuffoflegend-web/resources/addCircleChoice.js"></script>
<link rel="stylesheet" type="text/css" href="/stuffoflegend-web/css/theme.css">
</head>
<body>
	<form method="post">
		<fieldset id="circles">
			<input name="track" type="text"/>
			<jstl:forEach var="i" begin="0" end="6" step="1">
				<div class="circle">
					<input name="circle.${i}.name" type="text" />
					<input type="text" list="types" />
					<div id="circle.${i}"></div>
					<label onclick="addCircleChoice('circle.${i}')">Add ability
						for circle ${i+1}</label>
				</div>
			</jstl:forEach>
			<input type="submit" value="Submit" />

			<datalist id="types">
				<option>1</option>
				<option>2</option>
				<option>3</option>
			</datalist>
		</fieldset>
	</form>
</body>
</html>