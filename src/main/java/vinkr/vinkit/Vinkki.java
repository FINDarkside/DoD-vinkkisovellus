package vinkr.vinkit;

import vinkr.Varit;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Vinkki {

    protected static final String NL = System.getProperty("line.separator");

    protected String id;
    protected String nimi;
    protected String tyyppi;
    protected String vari;
    protected int lukuprosentti;

    public Vinkki(String tyyppi, String vari, String otsikko) {
        this.id = luoID();
        this.tyyppi = tyyppi;
        this.vari = vari;
        this.nimi = otsikko;
        this.lukuprosentti = 0;
    }

    // Getterit
    public String getID() {
        return this.id;
    }

    public String getTyyppi() {
        return this.tyyppi;
    }

    public String getOtsikko() {
        return this.nimi;
    }

    public int getLukuprosentti() {
        return this.lukuprosentti;
    }

    public String getVari() {
        return this.vari;
    }

    // Setterit
    public void setOtsikko(String otsikko) {
        this.nimi = otsikko;
    }

    public void setLukuprosentti(int prosentti) {
        this.lukuprosentti = prosentti;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    // Tulostus
    public String tulostaLukuprosentti() {
        String tuloste = this.getLukuprosentti() + "%" + this.vari;
        if (this.getLukuprosentti() == 0) {
            tuloste = Varit.PUNAINEN + tuloste;
        } else if (this.getLukuprosentti() == 100) {
            tuloste = Varit.VIHREA + tuloste;
        } else {
            tuloste = Varit.KELTAINEN + tuloste;
        }
        return tuloste;
    }

    abstract public String tulosta();
    abstract public String toString();

    // Linkin avaaminen
    abstract public void avaaLinkki() throws Exception;

    // Apumetodit
    private String luoID() {
        Date nykyhetki = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String id = ft.format(nykyhetki);
        return id;
    }
}
