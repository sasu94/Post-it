package model;

import java.io.Serializable;
import java.util.Date;

public class Spedizione implements Serializable {
	private static final long serialVersionUID = 6612084874015084939L;
	String codice, stato, nome, cognome, indirizzo, corriere;
	Date dataPrevistaConsegna;

	public Spedizione() {
		stato = "da Consegnare";
	}

	public Spedizione(String codice, String stato, String nome, String cognome, String indirizzo, String corriere,
			Date dataPrevistaConsegna) {
		super();
		this.codice = codice;
		this.stato = stato;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.corriere = corriere;
		this.dataPrevistaConsegna = dataPrevistaConsegna;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCorriere() {
		return corriere;
	}

	public void setCorriere(String corriere) {
		this.corriere = corriere;
	}

	public Date getDataPrevistaConsegna() {
		return dataPrevistaConsegna;
	}

	public void setDataPrevistaConsegna(Date dataPrevistaConsegna) {
		this.dataPrevistaConsegna = dataPrevistaConsegna;
	}
}
