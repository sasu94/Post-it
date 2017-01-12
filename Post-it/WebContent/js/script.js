$(document).ready(function() {
	checkSession();
	
	$("#login").click(function() {
		var mail = $('#mail');
		if (isEmail(mail.val()))
			Login();
		else
			mail.css('border-color', 'red');

	});
	$("#logout").click(function(e) {
		e.preventDefault();
		logout();
	});

	$("#register").click(function(e) {
		e.preventDefault();
		register();
	});
	

});


var b = false;

function register() {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			register : JSON.stringify($('#Rnome').val() + ";"
					+ $('#Rcogn').val() + ";" + $('#Raddr').val() + ";"
					+ $('#Rmail').val() + ";" + $('#Rpass').val()),
		},
		success : function(data) {
			alert('registrazione completata con successo');
			$('#register-modal').modal('hide');
		},
		fail : function() {
			alert('niente');
		}
	})
}

function checkSession() {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			session : JSON.stringify(" "),
		},
		success : function(data) {
			var res = data.split(";");
			if (res[0] == "true") {
				$('#user').toggleClass("disappear");
				$('#person').html(res[1]);
				$('#log').toggleClass('disappear');
			}
		},
		fail : function() {
			alert('niente');
		}
	})
}

function logout() {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			logout : JSON.stringify(" "),
		},
		success : function(data) {
			$('#user').toggleClass("disappear");
			prev.toggleClass("disappear");
			$('#menu').toggleClass('disappear');
			$('#log').toggleClass('disappear');
		},
		fail : function() {
			alert('niente');
		}

	})
}

function isEmail(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return regex.test(email);
}

function Login() {
	$.ajax({
		type : "POST",
		url : "Login",
		datatype : "json",
		data : {
			user : JSON.stringify( $('#mail').val()+";"+$('#pass').val()),
		},
		success : function(data) {
			var res = data.split(";");
			if (res[0] == "true") {
				$('#mail').val('');
				$('#pass').val('');
				$('#user').toggleClass("disappear");
				$('#person').html(res[1]);
				$('#log').toggleClass('disappear');
				$('.dropdown.open .dropdown-toggle').dropdown('toggle');
				switch(res[2]){
				case "S":
					prev=$('#segretaria');
					break;
				case "D":
					prev=$('#dirigente');
					break;
				}
				$('#menu').toggleClass('disappear');
				prev.removeClass('disappear');
			} else {
				alert('username or password invalid')
			}
		},
		fail : function() {
			alert('niente');
		}
	})
}
