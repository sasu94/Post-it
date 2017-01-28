$(document).ready(function(){
	$('.giacenza').click(function(e){
		e.preventDefault();
		var td=$(this).parent();
		$(this).remove();
		td.append($('<input id="giorni" style="width: 50px" min=1 value=1 type="number"><a class="fineGiacenza" href="#"><span class="glyphicon glyphicon-ok"></span></a>'));
		c=$(this).attr('on');
		$('.fineGiacenza').click(fineGiacenza);
	});
	
	$('.reso').click(fineReso);
	
	$('.fineTutteGiacenze').click(fineTutteGiacenze);
	$('.fineTuttiResi').click(fineTuttiResi);
	
	
});

function fineReso(e){
	e.preventDefault();
	var c=$(this).parent().parent().children().first().text();
	var tr=$(this).parent().parent();
	$.ajax({
		type : "POST",
		url : "Segretaria",
		datatype : "json",
		data : {
			reso : JSON.stringify(c),
		},
		success : function(data) {
			tr.remove();
		}
	})
}

function fineGiacenza(e){
	e.preventDefault();
	var giacenza={
		codice:c,
		giorni:$(this).prev().val()
	}
	var tr=$(this).parent().parent();
	$.ajax({
		type : "POST",
		url : "Segretaria",
		datatype : "json",
		data : {
			giacenza : JSON.stringify(giacenza),
		},
		success : function(data) {
			tr.remove();
		}
	})
}

function fineTutteGiacenze(e){
	e.preventDefault();
	if($('.g').length!=0){
		var g=$(this).prev().val();
		$.ajax({
			type : "POST",
			url : "Segretaria",
			datatype : "json",
			data : {
				allGiacenze : JSON.stringify(g),
			},
			success : function(data) {
				$('.g').remove();
			}
		})
	}else{
		alert('nessuna giacenza da risolvere');
	}
}

function fineTuttiResi(e){
	e.preventDefault();
	if($('.r').length!=0){
		$.ajax({
			type : "POST",
			url : "Segretaria",
			datatype : "json",
			data : {
				allResi : JSON.stringify("resi"),
			},
			success : function(data) {
				alert('stampando i documenti');
				$('.r').remove();
			}
		})
	}else{
		alert('nessun reso da risolvere')
	}
}