<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Spedizione</title>
</head>
<body>
	<%@include file="header.jsp"%>
	<section class="container mainBody">
	<div class="page-header">
		<h1>Inserisci una spedizione</h1>
	</div>
	<form class="form-horizontal" role="form" method="post"
		action="Segretaria?par=newShipping">
		<div class="form-group">
			<label for="firstname" class="col-sm-2 col-lg-2 control-label">Inserisci
				Codice Spedizione</label>
			<div class="col-sm-10 col-sm-10">
				<input required type="text" class="form-control" id="codice"
					name="codice" placeholder="Codice">
			</div>
		</div>
		<div class="form-group normal">
			<label for="firstname" class="col-sm-2 col-lg-2 control-label">Seleziona
				Corriere</label>
			<div class="col-sm-10 col-lg-10">
				<select required name="corriere" id="corriere" class="form-control">
					<c:forEach var="c" items="${corrieri}">
						<option value="${c.email}">${c.nome}${c.cognome}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="firstname" class="col-sm-2 col-lg-2 control-label">Inserisci
				informazioni destinatario</label>
		</div>
		<div class="form-group">
			<label for="nome" class="col-sm-2 col-lg-2 control-label">Nome</label>
			<div class="col-sm-10 col-lg-10">
				<input type=text name="nome" id="nome" placeholder="Nome"
					class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label for="cognome" class="col-sm-2 col-lg-2 control-label">Cognome</label>
			<div class="col-sm-10 col-lg-10">
				<input type=text name="cognome" id="cognome" placeholder="Cognome"
					class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label for="indirizzo" class="col-sm-2 col-lg-2 control-label">Indirizzo</label>
			<div class="col-sm-10 col-lg-10">
				<input type=text name="indirizzo" id="indirizzo"
					placeholder="Indirizzo" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label for="data" class="col-sm-2 col-lg-2 control-label">Data
				prevista di spedizione</label>
			<div class="col-sm-10 col-lg-10">
				<input type=date name="data" id="data" placeholder="Data"
					class="form-control">
			</div>
		</div>
		<div class="normal" class="form-group">
			<div class="col-sm-offset-2 col-lg-offset-2 col-sm-10 col-lg-10">
				<a id="add" disabled=true class="btn btn-default">Inserisci</a> <a
					id="end" disabled=true class="btn btn-default">Termina</a><input
					id="clear" class="disappear" type="reset">
			</div>
		</div>
		<div class="form-group">
			<label for="firstname" class="col-sm-2 col-lg-2 control-label"><a
				class="reso" href=#>Segnala Reso</a></label>
		</div>

		<div id="reso" class="disappear">
			<div class="form-group">
				<label for="causale" class="col-sm-2 col-lg-2 control-label">Causale</label>
				<div class="col-sm-10 col-lg-10">
					<input type=text name="causale" id="causale" placeholder="Causale"
						class="form-control">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-lg-offset-2 col-sm-10 col-lg-10">
					<a id="saveReso" href="#" class="btn btn-default">Salva</a>
				</div>
			</div>
		</div>
	</form>
	</section>

</body>
<script src="js/newShipping.js"></script>
</html>