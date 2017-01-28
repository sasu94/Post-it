package model;

import java.util.Date;

public class Reso {
	String codice, causale;
	Date data;

	public Reso() {

	}

	public Reso(String codice, String causale, Date dataInizio) {
		super();
		this.codice = codice;
		this.causale = causale;
		this.data = dataInizio;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date dataInizio) {
		this.data = dataInizio;
	}

}
