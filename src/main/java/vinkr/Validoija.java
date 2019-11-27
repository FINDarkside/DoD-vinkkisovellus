package vinkr;

public class Validoija {

    public Validoija() {
    }
    
    
    public boolean validoiOtsikko(String otsikko) {
        if (otsikko.equals("")) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validoiTekija(String tekija) {
        if (tekija.equals("") || tekija.matches("[\\p{L}.]+\\p{Z}?[\\p{L}.]*\\p{Z}?[\\p{L}.]*, [\\p{L}.]+\\p{Z}?[\\p{L}.]*\\p{Z}?[\\p{L}.]*") || tekija.matches("[\\p{L}]+\\p{Z}[IVX]+") || tekija.matches("[\\p{L}]+")) {
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
}
