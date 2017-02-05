<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ritiro reso</title>
</head>
<body>
<%@include file="header.jsp"%>

	<section class="container mainBody">
	<div class="page-header">
		<h1>Ritiro reso</h1>
	</div>
	<div class="form-group">
		<label for="data" class="col-sm-2 col-lg-2 control-label">Inserire il codice del pacco da ritirare</label>
		<div class="col-sm-8 col-lg-8">
			<input type="text" placeholder="Codice" id="codice" class="form-control">
		</div>
		<div class="col-sm-2 col-lg-2">
			<a class="btn btn-default" id="cerca">Cerca</a>
		</div>
	</div>
	</section>
</body>
<script src="js/ritiro.js"></script>
</html>