<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Leggi Report</title>
</head>
<body>
<%@include file="header.jsp"%>
<section class="container mainBody">
	<div class="page-header">
		<h1>Leggi Report</h1>
	</div>
	<form class="form-horizontal">
		<div class="form-group">
			<label for="corriere" class="col-sm-2 col-lg-2 control-label">Seleziona
				un corriere</label>
			<div class="col-sm-10 col-lg-10">
				<select required name="corriere" id="corriere" class="form-control">
					<option value="">Scegli</option>
					<c:forEach var="c" items="${corrieri}">
						<option value="${c.email}">${c.nome}${c.cognome}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="checkbox">
				<label for="tutti" class="col-sm-2 col-lg-2 control-label">Oppure scegli <a id=tutti href="#">tutti</a></label>
			</div>
		</div>
		<div class="form-group">
			<label for="corriere" class="col-sm-2 col-lg-2 control-label">Seleziona
				un periodo di tempo</label>
		</div>
		<div class="form-group">
			<label for="corriere" class="col-sm-2 col-lg-2 control-label">Dal</label>
			<div class="col-sm-10 col-lg-10">
				<input type="date" id="dataInizio">
			</div>
		</div>
		<div class="form-group">
			<label for="corriere" class="col-sm-2 col-lg-2 control-label">Al</label>
			<div class="col-sm-10 col-lg-10">
				<input type="date" id="dataFine">
			</div>
		</div>
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Codice</th>
					<th>Data</th>
					<th>Posizione</th>
					<th>Informazioni</th>
				</tr>
			</thead>
			<tbody>
					
			</tbody>
		</table>
	</div>
	</form>
</section>
</body><script src="js/report.js" type="text/javascript"></script>
</html>