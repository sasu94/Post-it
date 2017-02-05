<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ritarda Spedizione</title>
</head>
<body>
<%@include file="header.jsp"%>
	<section class="container">
		<div class="page-header">
			<h1>Gestisci spedizioni</h1>
		</div>
		

		
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#delay">Ritarda Spedizione</a></li>  
			<li><a data-toggle="tab" href="#riassegna">Riassegna Spedizioni</a></li>
		</ul>

	<div class="tab-content">
		 
		<div id="delay" class="tab-pane fade in active">
			<h2>Scegli una data in cui ritardare le consegne odierne</h2>
			<div class="form-group data">
				<label for="data" class="col-sm-2 col-lg-2 control-label">Data</label>
				<div class="col-sm-8 col-lg-8">
					<input type="date" placeholder="usare il formato yyyy-mm-gg" id="data" class="form-control">
				</div>
				<div class="col-sm-2 col-lg-2">
					<a class="btn btn-default" id="salva">Salva</a>
				</div>
			</div>
			     
		</div>
		 
		<div id="riassegna" class="tab-pane fade">
			<c:choose>
			<c:when test="${not empty corrieri}">
				<form class="form-horizontal" role="form" method="post"
					action="Dirigente?par=assign">
					<div class="form-group">
						<label for="corriere" class="col-sm-2 col-lg-2 control-label">Scegli corriere</label>
						<div class="col-sm-10 col-sm-10">
						  <c:forEach items="${corrieri}" var="c">
							<div class="radio">
			 					<label><input type="radio" name="cor" required value="${c.email }">${c.nome} ${c.cognome}</label>
							</div>
						</c:forEach>
						</div>
					</div>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Codice</th>
									<th>Corriere</th>
									<th>Indirizzo</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="s" items="${spedizioni}">
									<tr>
										<td><input name=spedizioni type="checkbox" value="${s.codice }"></td>
										<td>${s.corriere}</td>
										<td>${s.indirizzo}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<input type=submit>
				</form>
			</c:when>
			<c:otherwise>
				<h2>Nessun corriere da riassegnare</h2>
			</c:otherwise>
			</c:choose> 		     
		</div>
		 
	</div>
		
	</section>

</body>
<script type="text/javascript" src="js/manage.js"></script>
</html>