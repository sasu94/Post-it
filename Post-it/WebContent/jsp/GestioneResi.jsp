<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestione Resi</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<section class="container mainBody">
	<div class="page-header">
		<h1>Gestisci i resi</h1>
	</div>
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Codice</th>
					<th>Causale</th>
					<th>Data</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${resi.values()}">
					<tr class="r">
						<td>${c.codice}</td>
						<td>${c.causale}</td>
						<td>${c.data}</td>
						<td>
							<a class="reso" href="#" title="Stampa documento di reso">
								<span class="glyphicon glyphicon-print"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		Stampa tutti <a title="Stampa tutti" href="#" class="fineTuttiResi">
			<span class="glyphicon glyphicon-print"></span>
		</a>
	</div>
	<div class="page-header">
		<h1>Gestisci giacenze</h1>
	</div>
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>Codice</th>
					<th>Causale</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="c" items="${giacenze.values()}">
					<tr class="g">
						<td>${c.codice}</td>
						<td>${c.causale}</td>
						<td>${c.data}</td>
						<td style="width: 100px"><a class="giacenza"
							on="${c.codice }" href="#" title="gestisci giacenza"> <span
								class="glyphicon glyphicon-edit"></span>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		Risolvi tutti <input style="width: 50px" min=1 value=1 type="number">
		<a class="fineTutteGiacenze" href="#"> <span
			class="glyphicon glyphicon-ok"></span>
		</a>
	</div>
	</section>

</body>
<script src="js/gestioneResi.js"></script>
</html>