<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Feedback</title>
</head>
<body>
	<%@include file="header.jsp"%>

	<section class="container mainBody">
	<div class="page-header">
		<h1>Dai un feedback sul nostro servizio</h1>
	</div>

	<form class="form-horizontal" role="form" method="post" action="User?par=feedback">
		<div class="form-group">
			<label for="firstname" class="col-sm-2 col-lg-2 control-label">Inserisci
				Codice Spedizione</label>
			<div class="col-sm-10 col-sm-10">
				<input required type="text" class="form-control" id="codice"
					name="codice" placeholder="Codice">
			</div>
		</div>
		<div class="form-group">
			<label for="argomento" class="col-sm-2 col-lg-2 control-label">Scegli un argomento</label> 
			<div class="col-sm-10 col-lg-10">
				<select required name="argomento" id="argomento" class="form-control">
					<option>Corriere</option>
					<option>Spedizione</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="commento" class="col-sm-2 col-lg-2 control-label">Inserisci qui il tuo commento</label>
			<div class="col-sm-10 col-lg-10">
				<textarea required name="commento" id="commento" style=""
				class="form-control textArea" rows="3"></textarea>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-lg-offset-2 col-sm-10 col-lg-10">
				<button type="submit" class="btn btn-default">Inserisci</button>
			</div>
		</div>
	</form>
	</section>

</body>
</html>