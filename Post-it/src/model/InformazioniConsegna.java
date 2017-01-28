package model;

import java.util.Date;

public class InformazioniConsegna {
	String idSpedizione, posizione, informazioni;
	Date data;

	public InformazioniConsegna() {
	}

	public InformazioniConsegna(String idSpedizione, String posizione, String informazioni, Date data) {
		super();
		this.idSpedizione = idSpedizione;
		this.posizione = posizione;
		this.informazioni = informazioni;
		this.data = data;
	}

	public String getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(String idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public String getPosizione() {
		return posizione;
	}

	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	public String getInformazioni() {
		return informazioni;
	}

	public void setInformazioni(String informazioni) {
		this.informazioni = informazioni;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
