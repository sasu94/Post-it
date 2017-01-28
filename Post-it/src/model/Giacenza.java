package model;

public class Giacenza {
	String codice;
	int giorni;

	public Giacenza() {
	}

	public Giacenza(String codice, int giorni) {
		this.codice = codice;
		this.giorni = giorni;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public int getGiorni() {
		return giorni;
	}

	public void setGiorni(int giorni) {
		this.giorni = giorni;
	}
}
