$(document).ready(function(){
	$('#cerca').on('click',function(){
		var codice=$(this).parent().prev().children().val();
		$.ajax({
			type : "POST",
			url : "Segretaria",
			datatype : "json",
			mimeType: "textPlain",
			data : {
				ritiro : codice,
			},
			success : function(data) {
				var x=JSON.parse(data);
				if(x.exist==false){
					alert('il codice inserito non esiste');
				}else{
					if(x.scaduta==true)
						alert('giacenza scaduta');
					else
						alert('ritiro effettuato con successo');
				}
			},
			fail : function() {
				alert('niente');
			}
		})
	});
})