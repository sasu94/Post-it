$(document).ready(function() {
	$('#corriere').change(function() {
		var c = $(this).val();
		if (c != '') {
			if($('#dataInizio').val()!='' && $('#dataFine').val()!=''){
				$('tbody tr').remove();
				$.ajax({
					type : "POST",
					url : "Dirigente",
					datatype : "json",
					data : {
						corriere : c,
						dataInizio:$('#dataInizio').val(),
						dataFine:$('#dataFine').val()
					},
					success : function(d) {
						var a=JSON.parse(d);
						$.each(a,function(){
							var i=$(this)[0];
							if(i.posizione!='')
								i.posizione=' <a title="Visita su Maps" href="http://maps.google.com/?q='+i.posizione+'">'+i.posizione+'</a>'
							$('tbody').append($('<tr><td>'+i.idSpedizione+'</td><td>'+i.data+'</td><td>'+i.posizione+'</td><td>'+i.informazioni+'</td></tr>'));
						})
					}
				})
			}else{
				alert('scegli un periodo di tempo!');
				$(this).val('');
			}
		}
	});
	$('#tutti').click(function(e){
		e.preventDefault();
		if($('#dataInizio').val()!='' && $('#dataFine').val()!=''){
			$('tbody tr').remove();
			$('#corriere').val('');
			$.ajax({
				type : "POST",
				url : "Dirigente",
				datatype : "json",
				data : {
					tutti : '',
					dataInizio:$('#dataInizio').val(),
					dataFine:$('#dataFine').val()
				},
				success : function(d) {
					var a=JSON.parse(d);
					$.each(a,function(){
						var i=$(this)[0];
						if(i.posizione!='')
							i.posizione=' <a title="Visita su Maps" href="http://maps.google.com/?q='+i.posizione+'">'+i.posizione+'</a>'
						$('tbody').append($('<tr><td>'+i.idSpedizione+'</td><td>'+i.data+'</td><td>'+i.posizione+'</td><td>'+i.informazioni+'</td></tr>'));
					})
				}
			})
		}else{
			alert('scegli un periodo di tempo!');
		}
	});
	$('#dataInizio').focusout(dataGreat);
	$('#dataFine').focusout(dataGreat);

});


function dataGreat(){
	if($('#dataInizio').val()!=''&&$('#dataFine').val()!=''){
		$.ajax({
			type : "POST",
			url : "Dirigente",
			datatype : "json",
			data : {
				dataInizio:$('#dataInizio').val(),
				dataFine:$('#dataFine').val()
			},
			success : function(d) {
				var r=JSON.parse(d);
				if (!r.res) {
					$('#dataFine').val('');
					alert('la data finale deve essere posterione all\'iniziale')
				}
			}
		})
	}
}