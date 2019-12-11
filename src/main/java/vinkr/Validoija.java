package vinkr;

import java.time.Year;

public class Validoija {

    Vinkr app;

    public Validoija(Vinkr app) {
        this.app = app;
    }
    
    public boolean validoiOtsikko(String otsikko) {
        if (otsikko.equals("")) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validoiTekija(String tekija) {
        if (tekija.equals("") || tekija.matches(
                "[\\p{L}\\p{M}'.-]+\\p{Z}?[\\p{L}\\p{M}'.-]*\\p{Z}?[\\p{L}\\p{M}'.-]*, [\\p{L}\\p{M}'.-]+\\p{Z}?[\\p{L}\\p{M}'.-]*\\p{Z}?[\\p{L}\\p{M}'.-]*") || tekija.matches("[\\p{L}]+\\p{Z}[IVX]+") || tekija.matches("[\\p{L}]+")) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validoiIsbn(String isbn) {
        String siivottuIsbn = isbn.replaceAll("[- ]", "");
        if (isbn.equals("")) {
            return true;
        } else if (siivottuIsbn.matches("[0-9X]{10}")) {
            if (laskeVanhaIsbnTarkistus(siivottuIsbn) % 11 == 0) {
                return true;
            } else {
                return false;
            }
        } else if (siivottuIsbn.matches("[0-9]{13}")) {
            if (laskeUusiIsbnTarkistus(siivottuIsbn) % 10 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private int laskeVanhaIsbnTarkistus(String isbn) {
        int tarkistussumma = 0;
        for (int i = 0; i < 9; i++) {
            tarkistussumma = tarkistussumma + (10 - i) * Integer.parseInt(isbn.substring(i, i + 1));
        }
        if (isbn.substring(9).equals("X")) {
            tarkistussumma = tarkistussumma + 10;
        } else {
            tarkistussumma = tarkistussumma + Integer.parseInt(isbn.substring(9));
        }
        return tarkistussumma;
    }
    
    private int laskeUusiIsbnTarkistus(String isbn) {
        int tarkistussumma = 0;
        for (int i = 0; i < 13; i++) {
            if (i % 2 == 0) {
                tarkistussumma = tarkistussumma + Integer.parseInt(isbn.substring(i, i + 1));
            } else {
                tarkistussumma = tarkistussumma + (3 * Integer.parseInt(isbn.substring(i, i + 1)));
            }
        }
        return tarkistussumma;
    }
    
    public boolean validoiVuosi(String vuosi) {
        if (vuosi.matches("[0-9]{1,4}") && Integer.parseInt(vuosi) >= 0 && Integer.parseInt(vuosi) <=  Year.now().getValue()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean validoiLinkki(String linkki) {
        if (linkki.matches("[0-9]+")) {
            if (Integer.parseInt(linkki) > 0 && Integer.parseInt(linkki) <= app.getVinkit().size()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean validoiLukuprosentti(int prosentti) {
        if (prosentti >= 0 && prosentti <= 100) {
            return true;
        }

        return false;
    }
}
