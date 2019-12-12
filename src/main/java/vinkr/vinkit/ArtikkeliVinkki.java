package vinkr.vinkit;

import vinkr.Varit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArtikkeliVinkki extends Vinkki {

    public static final SimpleDateFormat PVMMUOTO = new SimpleDateFormat("dd.MM.yyy");
    private static final String VARI = Varit.SINIVIHREA;
    private static final String TYYPPI = "artikkeli";

    private String tekija = ""; // Tekijän nimi tallennetaan muodossa "Sukunimi, Etunimi"
    private String julkaisu = "";
    private Date julkaisupvm = null;
    private URL url;

    public ArtikkeliVinkki(URL url, String otsikko, String tekija) {
        super(TYYPPI, VARI, otsikko);
        this.tekija = tekija;
        this.url = url;
    }
    
    // Getterit
    public String getTekija() {
        return this.tekija;
    }

    public String getTekijanSukunimi() {
        return this.tekija.substring(0, this.tekija.indexOf(","));
    }

    public String getTekijanEtunimi() {
        return this.tekija.substring(this.tekija.indexOf(",") + 2);
    }
    
    public String getJulkaisu() {
        return this.julkaisu;
    }

    public Date getJulkaisupvm() {
        return this.julkaisupvm;
    }
    
    public URL getUrl() {
        return this.url;
    }

    // Setterit
    public void setTekija(String tekija) {
        this.tekija = tekija;
    }

    public void setJulkaisu(String julkaisu) {
        this.julkaisu = julkaisu;
    }

    public void setJulkaisupvm(Date julkaisupvm) {
        this.julkaisupvm = julkaisupvm;
    }
    
    public void setUrl(URL url) {
        this.url = url;
    }
    
    // Tulostus
    @Override
    public String tulosta() {
        String tuloste = "Tyyppi: Artikkeli" + NL + "Otsikko: " + this.getOtsikko() + NL;
        if (!this.getTekija().equals("")) {
            tuloste += "Kirjoittaja: " + this.getTekija() + NL;
        }
        if (!this.getJulkaisu().equals("")) {
            tuloste += "Lähde: " + getJulkaisu() + NL;
        }
        if (this.getJulkaisupvm() != null) {
            tuloste += "Julkaistu: " + PVMMUOTO.format(this.getJulkaisupvm()) + NL;
        }
        tuloste += "URL: " + this.getUrl().toString() + NL;
        tuloste += "Luettu: " + super.tulostaLukuprosentti() + NL;

        return tuloste;
    }
    
    @Override
    public String toString() {
        String string = this.getOtsikko();
        if (this.getJulkaisupvm() != null) {
            string += " (" + PVMMUOTO.format(this.getJulkaisupvm()) + ")";
        }
        string += " [" + this.getUrl().toString() + "]";
        return string;
    }
    
    // Linkin avaaminen
    @Override
    public void avaaLinkki() throws IOException, URISyntaxException {
        java.awt.Desktop.getDesktop().browse(this.getUrl().toURI());
    }
}
