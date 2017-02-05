<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Traccia Spedizione</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container">
	<div class="page-header">
		<h1>Traccia Spedizione</h1>
	</div>
	<form>
		<div class="form-group">
			<label for="codice" class="col-sm-2 col-lg-2 control-label">Seleziona
				un corriere</label>
			<div class="col-sm-8 col-lg-8">
				<input type="text" id="codice" class="form-control">
			</div>
			<div class="col-sm-2 col-lg-2">
				<a class="btn btn-default" id="cerca">Cerca</a>
			</div>
		</div>
	</form>
	<div class="table-responsive tracking">
	
	</div>
	
	<div class="map">
		
	</div>

</section>
</body>
<script type="text/javascript" src="js/track.js"></script>
</html>