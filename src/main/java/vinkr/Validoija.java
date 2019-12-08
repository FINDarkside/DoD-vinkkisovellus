package vinkr;

import java.time.Year;

public class Validoija {
    Vinkr app;

    public Validoija(Vinkr app) {
        this.app = app;
    }
    
    public boolean validoiOtsikko(String otsikko) {
        return !otsikko.equals("");
    }
    
    public boolean validoiTekija(String tekija) {
        return tekija.equals("") 
                || tekija.matches(
                "[\\p{L}\\p{M}.-]+\\p{Z}?[\\p{L}\\p{M}.-]*\\p{Z}?[\\p{L}\\p{M}.-]*, [\\p{L}\\p{M}.-]+\\p{Z}?[\\p{L}\\p{M}.-]*\\p{Z}?[\\p{L}\\p{M}.-]*")
                || tekija.matches("[\\p{L}]+\\p{Z}[IVX]+")
                || tekija.matches("[\\p{L}]+");
    }
    
    public boolean validoiIsbn(String isbn) {
        String siivottuIsbn = isbn.replaceAll("[- ]", "");
        
        if (isbn.equals("")) {
            return true;
        } 
        
        if (siivottuIsbn.matches("[0-9X]{10}")) {
            return laskeVanhaIsbnTarkistus(siivottuIsbn) % 11 == 0;
        }
        
        if (siivottuIsbn.matches("[0-9]{13}")) {
            return laskeUusiIsbnTarkistus(siivottuIsbn) % 10 == 0;
        } 
        
        return false;
    }
    
    private int laskeVanhaIsbnTarkistus(String isbn) {
        int tarkistussumma = 0;
        for (int i = 0; i < 9; i++) {
            tarkistussumma += (10 - i) * Integer.parseInt(isbn.substring(i, i + 1));
        }
        
        tarkistussumma += isbn.substring(9).equals("X")
                ? 10 : Integer.parseInt(isbn.substring(9));
        
        return tarkistussumma;
    }
    
    private int laskeUusiIsbnTarkistus(String isbn) {
        int tarkistussumma = 0;
        
        for (int i = 0; i < 13; i++) {
            tarkistussumma += i % 2 == 0
                    ? Integer.parseInt(isbn.substring(i, i + 1))
                    : 3 * Integer.parseInt(isbn.substring(i, i + 1));
        }
        
        return tarkistussumma;
    }
    
    public boolean validoiVuosi(String vuosi) {
        return vuosi.matches("[0-9]{1,4}")
                && Integer.parseInt(vuosi) >= 0
                && Integer.parseInt(vuosi) <=  Year.now().getValue();
    }
    
    public boolean validoiLinkki(String linkki) {
        return linkki.matches("[0-9]+")
                && Integer.parseInt(linkki) > 0
                && Integer.parseInt(linkki) <= app.getVinkit().size();
    }
    
    
}
