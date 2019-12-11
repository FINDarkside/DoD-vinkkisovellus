package vinkr.vinkit;

import vinkr.Varit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class KirjaVinkki extends Vinkki {

    private static final String VARI = Varit.SININEN;
    private static final String TYYPPI = "kirja";

    private ArrayList<String> tekijat; // Tekijöiden nimet tallennetaan taulukkolistaan muodossa "Sukunimi, Etunimi"
    private String isbn = "";
    private int julkaisuvuosi = 0;
    private String julkaisupaikka = "";
    private String kustantaja = "";

    public KirjaVinkki(String otsikko, ArrayList<String> kirjoittajat, String isbn) {
        super(TYYPPI, VARI, otsikko);
        this.tekijat = kirjoittajat;
        this.isbn = isbn;
    }

    // Getterit
    public int getTekijoidenMaara() {
        if (this.tekijat == null) {
            return 0;
        }
        return this.tekijat.size();
    }
    
    public String getTekija(int numero) {
        if (numero > 0 && numero <= this.tekijat.size()) {
            return this.tekijat.get(numero - 1);
        } else {
            throw new IllegalArgumentException("Teoksella on vain " + this.tekijat.size() + " tekijä(ä).");
        }
    }
    
    public String getTekijanSukunimi(int numero) {
        if (numero > 0 && numero <= this.tekijat.size()) {
            return this.tekijat.get(numero - 1).substring(0, this.tekijat.get(numero - 1).indexOf(","));
        } else {
            throw new IllegalArgumentException("Teoksella on vain " + this.tekijat.size() + " tekijä(ä).");
        }
    }

    public String getTekijanEtunimi(int numero) {
        if (numero > 0 && numero <= this.tekijat.size()) {
            return this.tekijat.get(numero - 1).substring(this.tekijat.get(numero - 1).indexOf(",") + 2);
        } else {
            throw new IllegalArgumentException("Teoksella on vain " + this.tekijat.size() + " tekijä(ä).");
        }
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
    public void lisaaTekija(String tekija) {
        this.tekijat.add(tekija);
    }
    
    public void poistaTekija(int numero) {
        if (numero > 0 && numero <= this.tekijat.size()) {
            this.tekijat.remove(numero - 1);
        } else {
            throw new IllegalArgumentException("Teoksella on vain " + this.tekijat.size() + " tekijä(ä).");
        }
    }
    
    public void korvaaTekija(int numero, String tekija) {
        if (numero > 0 && numero <= this.tekijat.size()) {
            this.tekijat.set(numero - 1, tekija);
        } else {
            throw new IllegalArgumentException("Teoksella on vain " + this.tekijat.size() + " tekijä(ä).");
        }
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
        String tuloste = "Tyyppi: Kirja" + NL;
        if (this.getTekijoidenMaara() == 1) {
            tuloste += "Tekijä: " + this.getTekija(1) + NL;
        } else if (this.getTekijoidenMaara() > 1) {
            tuloste += "Tekijät: " + tulostaTekijaluettelo() + NL;
        }
        tuloste += "Nimeke: " + super.getOtsikko() + NL;
        if (!this.julkaisupaikka.equals("") || !this.kustantaja.equals("") || this.julkaisuvuosi != 0) {
            tuloste += "Julkaisutiedot: " + getJulkaisutiedot() + NL;
        }
        if (!this.isbn.equals("")) {
            tuloste += "ISBN: " + this.isbn + NL;
        }
        tuloste += "Luettu: " + super.tulostaLukuprosentti() + NL;

        return tuloste;
    }

    private String tulostaTekijaluettelo() {
        String luettelo = this.getTekija(1);
        for (int i = 2; i < this.getTekijoidenMaara(); i++) {
            luettelo += "; " + this.getTekijanEtunimi(i) + " " + this.getTekijanSukunimi(i);
        }
        luettelo += " ja " + this.getTekijanEtunimi(this.getTekijoidenMaara()) + " " + this.getTekijanSukunimi(this.getTekijoidenMaara());
        return luettelo;
    }
    
    @Override
    public String toString() {
        String string = "";
        if (this.getTekijoidenMaara() > 0) {
            string += this.getTekijanSukunimi(1);
            if (this.getTekijoidenMaara() > 1) {
                for (int i = 2; i < this.getTekijoidenMaara(); i++) {
                    string += ", " + this.getTekijanSukunimi(i); 
                }
                string += " ja " + this.getTekijanSukunimi(this.getTekijoidenMaara());
            }
            string += ": ";
        }
        string += super.getOtsikko();
        if (this.julkaisuvuosi != 0) {
            string += " (" + this.getJulkaisuvuosi() + ")";
        }
        return string;
    }

    // Linkin avaaminen
    @Override
    public void avaaLinkki() throws Exception {
        throw new Exception();
    }
}
