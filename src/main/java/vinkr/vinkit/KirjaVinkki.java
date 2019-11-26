package vinkr.vinkit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KirjaVinkki implements Vinkki {

	public static final String NL = System.getProperty("line.separator");
	private String id;
	private String nimeke;
	private String tyyppi = "kirja";
	private String tekija = ""; // Tekijän nimi tallennetaan muodossa "Sukunimi, Etunimi"
	private String isbn = "";
	private int julkaisuvuosi = 0;
	private String julkaisupaikka = "";
	private String kustantaja = "";
	
	
	public KirjaVinkki(String otsikko) {
		this.id = luoID();
		this.nimeke = otsikko;
	}
	
	// Getterit
	
	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public String getOtsikko() {
		return this.nimeke;
	}

	@Override
	public String getTyyppi() {
		return this.tyyppi;
	}
	
	public String getTekija() {
		return this.tekija;
	}

	public String getTekijanSukunimi() {
		return this.tekija.substring(0, this.tekija.indexOf(","));
	}
	
	public String getTekijanEtunimi() {
		return this.tekija.substring(this.tekija.indexOf(",") + 2);
	}
	
	public String getISBN() {
		return this.isbn;
	}

	public int getJulkaisuvuosi() {
		return this.julkaisuvuosi;
	}

	public String getJulkaisupaikka() {
		return this.julkaisupaikka;
	}

	public String getKustantaja() {
		return this.kustantaja;
	}
	
	public String getJulkaisutiedot() {
		String julkaisutiedot = "";
		if (!this.julkaisupaikka.equals("")) {
			julkaisutiedot += this.getJulkaisupaikka();
			if (!this.kustantaja.equals("")) {
				julkaisutiedot += ": ";
			}
		}
		if (!this.kustantaja.equals("")) {
			julkaisutiedot += this.getKustantaja();
		}
		if (this.julkaisuvuosi != 0) {
			if (!this.kustantaja.equals("") || !this.julkaisupaikka.equals("")) {
				julkaisutiedot += ", ";
			}
			julkaisutiedot += this.getJulkaisuvuosi();
		}
		return julkaisutiedot;
	}
	

	// Setterit
	
	@Override
	public void setOtsikko(String otsikko) {
		this.nimeke = otsikko;
	}
	
	public void setTekija(String tekija) {
		this.tekija = tekija;
	}
	
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public void setJulkaisuvuosi(int julkaisuvuosi) {
		this.julkaisuvuosi = julkaisuvuosi;
	}
	
	public void setJulkaisupaikka(String julkaisupaikka) {
		this.julkaisupaikka = julkaisupaikka;
	}
	
	public void setKustantaja(String kustantaja) {
		this.kustantaja = kustantaja;
	}
	
	// Tulostus
	
	@Override
	public String tulosta() {
		String tuloste = "";
		if (!this.tekija.equals("")) {
			tuloste += "Tekijä: " + this.tekija + NL; 
		}
		tuloste += "Nimeke: " + this.nimeke + NL;
		if (!this.julkaisupaikka.equals("") || !this.kustantaja.equals("") || this.julkaisuvuosi != 0) {
			tuloste += "Julkaisutiedot: " + getJulkaisutiedot() + NL;
		}
		if (!this.isbn.equals("")) {
			tuloste += "ISBN: " + this.isbn + NL;
		}
		return tuloste;
	}

	@Override
	public String toString() {
		String string = "";
		if (!this.tekija.equals("")) {
			string += this.getTekijanSukunimi() + ": ";
		}
		string += this.nimeke;
		if (this.julkaisuvuosi != 0) {
			string += " (" + this.getJulkaisuvuosi() + ")";
		}
		return string;
	}

	// Apumetodit
	
	private String luoID() {
		Date nykyhetki = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String id = ft.format(nykyhetki);
        return id;
	}

}
