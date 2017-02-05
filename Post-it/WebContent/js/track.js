$(document).ready(function(){
	$('#cerca').on('click',function(e){
		e.preventDefault();
		var c=$(this).parent().prev().children().val();
		$('.map').children().remove();
		$('.table').remove();
		$.ajax({
			type : "POST",
			url : "User",
			datatype : "json",
			mimeType: "textPlain",
			data : {
				codiceTracking : c,
			},
			success : function(d) {
				var x=JSON.parse(d);
				if(x.exist==false){
					alert('il codice inserito non \xE8 valido');
				}else{
					x.list.forEach(function(i){
						$('.table-responsive').append('<table class="table"><thead><tr><th>Data</th><th>Posizione</th><th>Informazioni</th></tr></thead><tbody></tbody></table>');
						$('tbody').append('<tr><td>'+i.data+'</td><td>'+i.posizione+'</td><td>'+i.informazioni+'</td></tr>');
					})
					$('.map').append('<h3>Ultima posizione</h3><iframe width="600" height="450" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?key=AIzaSyBrQG5as2-rOn2Faiv11VI24FMTDXCZ0E0&q='+x.last+'" allowfullscreen></iframe>');
				}
			}
		})
	})
})