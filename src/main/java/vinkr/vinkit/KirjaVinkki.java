package vinkr.vinkit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KirjaVinkki implements Vinkki {

	public static final String NL = System.getProperty("line.separator");
	private String id;
	private String nimeke;
	private String tyyppi = "kirja";
	private String tekija = ""; // Tekijän nimi tallennetaan muodossa "Sukunimi, Etunimi"
	private String ISBN = "";
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
		return this.tekija.substring(0, this.tekija.indexOf(",") - 1);
	}
	
	public String getTekijanEtunimi() {
		return this.tekija.substring(this.tekija.indexOf(",") + 2);
	}
	
	public String getISBN() {
		return this.ISBN;
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

	// Setterit
	
	@Override
	public void setOtsikko(String otsikko) {
		this.nimeke = otsikko;
	}
	
	public void setTekija(String tekija) {
		this.tekija = tekija;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
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
			tuloste += muotoileJulkaisutiedot() + NL;
		}
		if (!this.ISBN.equals("")) {
			tuloste += "ISBN: " + this.ISBN + NL; 
		}
		return tuloste;
	}

	@Override
	public String toString() {
		return this.getTekijanSukunimi() + ": " + this.getOtsikko() + "(" + this.getJulkaisuvuosi() + ")";
	}

	// Apumetodit
	
	private String luoID( ) {
		Date nykyhetki = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String id = ft.format(nykyhetki);
        return id;
	}
	
	private String muotoileJulkaisutiedot() {
		String julkaisutiedot = "Julkaisutiedot: ";
		if (!this.julkaisupaikka.equals("")) {
			julkaisutiedot += this.julkaisupaikka;
			if (!this.kustantaja.equals("")) {
				julkaisutiedot += ": ";
			} else if (this.julkaisuvuosi != 0) {
				julkaisutiedot += ", ";
			}
		}
		if (!this.kustantaja.equals("")) {
			julkaisutiedot += this.kustantaja;
			if (this.julkaisuvuosi != 0) {
				julkaisutiedot += ", ";
			}
		}
		if (this.julkaisuvuosi != 0) {
			julkaisutiedot += this.julkaisuvuosi;
		}
		return julkaisutiedot;
	}

}
