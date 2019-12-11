package vinkr.vinkit;

import vinkr.Varit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YoutubeVinkki extends Vinkki {

    public static final SimpleDateFormat PVMMUOTO = new SimpleDateFormat("dd.MM.yyy");
    private static final String VARI = Varit.VIOLETTI;
    private static final String TYYPPI = "youtube";

    private String kanava;
    private Date julkaisupvm = null;
    private URL url;
    
    public YoutubeVinkki(URL url, String otsikko, String kanava) {
        super(TYYPPI, VARI, otsikko);
        this.kanava = kanava;
        this.url = url;
    }
    
 // Getterit
    public String getKanava() {
        return this.kanava;
    }
    
    public Date getJulkaisupvm() {
        return this.julkaisupvm;
    }
    
    public URL getUrl() {
        return this.url;
    }
    
 // Setterit
    public void setKanava(String kanava) {
        this.kanava = kanava;
    }

    public void setJulkaisupvm(Date julkaisupvm) {
        this.julkaisupvm = julkaisupvm;
    }
    
    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public void setLukuprosentti(int prosentti) {
        this.lukuprosentti = prosentti;
    }

    // Tulostus
    @Override
    public String tulosta() {
        String tuloste = "Tyyppi: YouTube-video" + NL + "Otsikko: " + this.getOtsikko() + NL;
        if (!this.getKanava().equals("")) {
            tuloste += "Kanava: " + this.getKanava() + NL;
        }
        if (this.getJulkaisupvm() != null) {
            tuloste += "Julkaistu: " + PVMMUOTO.format(this.getJulkaisupvm()) + NL;
        }
        tuloste += "URL: " + this.getUrl().toString() + NL;
        tuloste += "Katsottu: " + super.tulostaLukuprosentti() + NL;

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
