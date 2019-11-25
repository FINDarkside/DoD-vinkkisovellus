package vinkr.vinkit;

public class KirjaVinkki implements Vinkki {

    private String kirjoittaja;
    private String otsikko;
    private String isbn;

    public KirjaVinkki(String kirjoittaja, String otsikko, String isbn) {
        this.kirjoittaja = kirjoittaja;
        this.otsikko = otsikko;
        this.isbn = isbn;
    }

    public String getKirjoittaja() {
        return kirjoittaja;
    }

    public void setKirjoittaja(String kirjoittaja) {
        this.kirjoittaja = kirjoittaja;
    }

    @Override
    public String getOtsikko() {
        return otsikko;
    }

    @Override
    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTyyppi() {
        return "Kirja";
    }

    @Override
    public void setTyyppi(String tyyppi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String tulosta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
