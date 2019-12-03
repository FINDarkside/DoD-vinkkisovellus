package vinkr;

import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUI {

    private final Vinkr app;
    private final Scanner input;
    private final PrintStream output;
    private final Validoija validoija;

    public TextUI(Vinkr app, InputStream inputStream, OutputStream outputStream) {
        this.app = app;
        this.input = new Scanner(inputStream);
        this.output = new PrintStream(outputStream);
        this.validoija = new Validoija();
    }

    public void run() {
        listaaKomennot();

        while (true) {
            String komento = getInput("Komento");
            if (komento.equals("lopeta")) {
                break;
            }
            kasitteleKomento(komento);
            tulostaVinkkiApukomennosta();
        }
    }

    private void listaaKomennot() {
        output.println("Kaikki käytettävissä olevat komennot:");
        output.println("apua: Tulostaa tämän listan uudestaan");
        output.println("lisaa: Lisää kirja");
        output.println("listaa: Listaa kaikki lukuvinkit");
        output.println("lopeta: Sulkee sovelluksen");
    }

    private void kasitteleKomento(String komento) {
        switch (komento) {
            case "lisaa":
                lisaaKirja();
                break;
            case "listaa":
                listaaVinkit();
                break;
            case "apua":
                listaaKomennot();
                break;
            default:
                output.println("Komentoa ei tunnistettu");
        }
    }

    private void tulostaVinkkiApukomennosta() {
        output.println();
        output.println("Jos tahdot nähdä kaikki komennot, kirjoita 'apua'");
    }

    private void lisaaKirja() {
        String otsikko = kysyOtsikko();
        String kirjoittaja = kysyKirjoittaja();
        String isbn = kysyIsbn();
        KirjaVinkki kirjaVinkki = new KirjaVinkki(otsikko, kirjoittaja, isbn);
        app.lisaaVinkki(kirjaVinkki);
        output.println("Kirja lisätty");
    }

    private String kysyOtsikko() {
        while (true) {
            String otsikko = getInput("Otsikko");
            if (validoija.validoiOtsikko(otsikko) == true) {
                return otsikko;
            } else {
                output.println("Virhe: Otsikko ei saa olla tyhjä");
            }
        }
    }
    
    private String kysyKirjoittaja() {
        while (true) {
            String kirjoittaja = getInput("Kirjoittaja");
            if (validoija.validoiTekija(kirjoittaja) == true) {
                return kirjoittaja;
            } else {
                output.println("Virhe: Syötä kirjoittajan nimi muodossa 'Sukunimi, Etunimi'");
            }
        }
    }
    
    private String kysyIsbn() {
        while (true) {
            String isbn = getInput("ISBN");
            if (validoija.validoiIsbn(isbn) == true) {
                return isbn;
            } else {
                output.println("Virhe: Anna kelvollinen ISBN");
            }
        }
    }
    
    private void listaaVinkit() {
        output.println();
        for (Vinkki vinkki : app.getVinkit()) {
            output.println(vinkki.getTyyppi().substring(0, 1).toUpperCase() + vinkki.getTyyppi().substring(1));
            output.println(vinkki.tulosta());
        }
    }

    private String getInput(String name) {
        output.print(name + ": ");
        return input.nextLine();
    }

}
