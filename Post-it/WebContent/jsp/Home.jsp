<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post-it</title>
</head>
<body>
	<%@include file="header.jsp"%>

	<header>
	<div class="header-content">
		<div class="header-content-inner">
			<h1 id="homeHeading">Post-it</h1>
			<hr>
			<p>Scegli noi per le tue spedizioni!</p>
		</div>
	</div>
	</header>
	
	<section class="no-padding disappear" id="dirigente">
	<div class="container-fluid">
		<div class="row no-gutter popup-gallery">
			<div class="col-lg-4 col-sm-6">
				<a href="Dirigente?action=feedback" class="portfolio-box"> <img
					src="img/portfolio/thumbnails/leggiFeedback.jpg"
					class="img-responsive" alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Visualizza feedback</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 col-sm-6">
				<a href="Dirigente?action=report" class="portfolio-box"> <img
					src="img/portfolio/thumbnails/report.jpg" class="img-responsive"
					alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Visualizza Report</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 col-sm-6">
				<a href="Dirigente?action=manage" class="portfolio-box"> <img
					src="img/portfolio/thumbnails/manage.jpg" class="img-responsive"
					alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Gestisci spedizioni</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	</section>

	<section class="no-padding disappear" id="segretaria">
	<div class="container-fluid">
		<div class="row no-gutter popup-gallery">
			<div class="col-lg-4 col-sm-6">
				<a href="Segretaria?action=inserimento" class="portfolio-box"><img
					src="img/portfolio/thumbnails/inserire.jpg" class="img-responsive"
					alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Inserisci spedizioni</div>
						</div>
					</div> 
				</a>
			</div>
			<div class="col-lg-4 col-sm-6">
				<a href="Segretaria?action=reso" class="portfolio-box"> <img
					src="img/portfolio/thumbnails/reso.jpg" class="img-responsive"
					alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Risolvi reso</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 col-sm-6">
				<a href="Segretaria?action=ritiro" class="portfolio-box"> <img
					src="img/portfolio/thumbnails/ritiro.jpg" class="img-responsive"
					alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Ritiro reso</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	</section>

	<section class="no-padding" id="menu">
	<div class="container-fluid">
		<div class="row no-gutter popup-gallery">
			<div class="col-lg-4 col-sm-6">
				<a href="User?action=feedback" class="portfolio-box"> <img
					src="img/portfolio/thumbnails/feedback.jpg" class="img-responsive"
					alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Dai un feedback sul nostro
								servizio</div>
						</div>
					</div>
				</a>
			</div>
			<div class="col-lg-4 col-sm-6">
				<a href="User?action=track" class="portfolio-box"> <img
					src="img/portfolio/thumbnails/Airplanes.jpg" class="img-responsive"
					alt="">
					<div class="portfolio-box-caption">
						<div class="portfolio-box-caption-content">
							<div class="project-name">Traccia la tua spedizione</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	</section>
</body>
</html>