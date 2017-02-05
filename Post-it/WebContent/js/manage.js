$(document).ready(function(){
	$('#salva').on('click',function(){
		var data=$(this).parent().prev().children().val();
		$.ajax({
			type : "POST",
			url : "Dirigente",
			datatype : "json",
			mimeType: "textPlain",
			data : {
				delay : data,
			},
			success : function(d) {
				var x=JSON.parse(d);
				if(x.bool==false)
					alert('la data deve essere successiva a quella odierna');
				else{
					if(x.giorno==true)
						alert('la data non deve essere sabato o domenica');
					else{
						alert('spedizioni ritardate con successo');
					}
				}
			}
		})
	})
})