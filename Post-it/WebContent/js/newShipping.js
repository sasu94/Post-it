$(document).ready(function(){
	$('#add').click(function(){
		if($('#add').attr('disabled')!='disabled'){
			if($('#codice').val()!='' && $('#corriere').val()!='' && $('#nome').val()!='' && $('#cognome').val()!='' && $('#indirizzo').val()!='' && $('#data').val()!=''){
				spedizioni.push(new spedizione());
				$('#clear').click();
				if($('#end').attr('disabled')=='disabled')
					$('#end').removeAttr('disabled');
			}else{
				alert('compilare tutti i campi!!!')
			}
		}
	});
	$('#end').click(function(){
		if($('#end').attr('disabled')!='disabled'){
			sendToServer();
			contatti=[]
			$('#end').attr('disabled','disabled')
		}
	});
	$('#data').focusout(function(){
		controllaData($(this).val());
	});
	
	$('#codice').focusout(function(){
		controllaCodice($(this).val());
	});
	
	$('.reso').click(function(e){
		e.preventDefault();
		$('#reso').toggleClass('disappear');
		$('.normal').toggleClass('disappear');
	})
	
	$('#saveReso').click(function(e){
		e.preventDefault();
		if($('#codice').val()!='' && $('#corriere').val()!='' && $('#nome').val()!='' && $('#cognome').val()!='' && $('#indirizzo').val()!='' && $('#data').val()!='' && $('#causale').val()!=''){
			saveReso();
		}
	})
	
});

function controllaData(val){
	
	$.ajax({
		type : "POST",
		url : "Segretaria",
		datatype : "json",
		mimeType: "textPlain",
		data : {
			date : val,
		},
		success : function(data) {
			if(data==1)
				alert('la data inserita deve essere successiva alla data odierna');
			else if(data==2)
				alert('la data inserita non deve essere Sabato o Domenica');
			else
				$('#add').removeAttr('disabled');
		}
	})
}

function controllaCodice(val){
	$.ajax({
		type : "POST",
		url : "Segretaria",
		datatype : "text",
		data : {
			codice : val,
		},
		success : function(data) {
			if(data=="esistente"){
				alert('il codice è già esistente');
				$('#codice').val('');
			}
		}
	})
}

function spedizione(){
	this.codice=$('#codice').val(),
	this.corriere=$('#corriere').val(),
	this.nome=$('#nome').val(),
	this.cognome=$('#cognome').val(),
	this.indirizzo=$('#indirizzo').val(),
	this.dataPrevistaConsegna=$('#data').val()
}

var spedizioni=[];

function sendToServer(){
	$.ajax({
		type : "POST",
		url : "Segretaria",
		datatype : "json",
		data : {
			spedizioni : JSON.stringify(spedizioni),
		},
		success : function(data) {
			alert('registrazione completata con successo');
		},
		fail : function() {
			alert('niente');
		}
	})
}

function saveReso(){
	var reso={
			codice:$('#codice').val(),
			causale:$('#causale').val()
	}
	var s=new spedizione();
	s['stato']='Reso';
	s['corriere']=null;
	$.ajax({
		type : "POST",
		url : "Segretaria",
		datatype : "json",
		data : {
			newReso : JSON.stringify(reso),
			spedizione:JSON.stringify(s)
		},
		success : function(data) {
			alert('reso salvato con successo');
			$('#clear').click();
			$('#reso').toggleClass('disappear');
			$('.normal').toggleClass('disappear');
		},
		fail : function() {
			alert('niente');
		}
	})
}