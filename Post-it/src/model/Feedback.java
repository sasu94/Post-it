package model;

import java.util.Date;

public class Feedback {
	String codice, argomento, commento;
	Date data;

	public Feedback() {
	}

	public Feedback(String codice, String argomento, String commento) {
		super();
		this.codice = codice;
		this.argomento = argomento;
		this.commento = commento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getArgomento() {
		return argomento;
	}

	public void setArgomento(String argomento) {
		this.argomento = argomento;
	}

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

}
