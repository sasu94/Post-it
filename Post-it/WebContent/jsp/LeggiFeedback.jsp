<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leggi Feedback</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container mainBody">
<div class="page-header">
		<h1>Leggi i feedback</h1>
	</div>
<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Codice</th>
					<th>Argomento</th>
					<th>Commento</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="f" items="${feedback}">
					<tr>
						<td>${f.codice}</td>
						<td>${f.argomento}</td>
						<td>${f.commento}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>
</body>
</html>