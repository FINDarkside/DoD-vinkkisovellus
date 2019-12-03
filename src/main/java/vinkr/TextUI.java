package vinkr;

import vinkr.vinkit.ArtikkeliVinkki;
import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;
import vinkr.vinkit.YoutubeVinkki;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
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
        output.println("  apua: Tulostaa tämän listan uudestaan");
        output.println("  lisaa: Lisää uusi lukuvinkki");
        output.println("  listaa: Listaa kaikki lukuvinkit");
        output.println("  lopeta: Sulkee sovelluksen");
    }

    private void kasitteleKomento(String komento) {
        switch (komento) {
            case "lisaa":
                lisaaVinkki();
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

    private URL kysyUrl() {
        while (true) {
            String osoite = getInput("Videon osoite");

            try {
                URL url = new URL(osoite);
                if (true) { //validoija.validoiUrl
                    return url;
                } else {
                    output.println("Virhe: Anna kelvollinen URL-osoite");
                }
            } catch (MalformedURLException e) {
                output.println("Virhe: Anna kelvollinen URL-osoite");
            }
        }
    }

    private void lisaaVinkki() {
        output.println("Tuetut vinkkityypit ovat 'kirja', 'youtube' ja 'artikkeli'" + "\n" + "Takaisin pääset kirjoittamalla 'takaisin'");
        String tyyppi = getInput("Vinkin tyyppi");
        switch (tyyppi) {
            case "kirja":
                lisaaKirja();
                break;
            case "youtube":
                lisaaVideo();
                break;
            case "artikkeli":
                lisaaArtikkeli();
                break;
            case "takaisin":
                break;
            default:
                output.println("Vinkin tyyppiä ei tunnistettu");
                lisaaVinkki();
        }
    }

    private void lisaaVideo() {
        URL url = kysyUrl();
        String otsikko = kysyOtsikko();
        String kanava = kysyKanava();
        YoutubeVinkki youtubeVinkki = new YoutubeVinkki(url, otsikko, kanava);
        app.lisaaVinkki(youtubeVinkki);
        output.println("Video lisätty");
    }

    private void lisaaArtikkeli() {
        String otsikko = kysyOtsikko();
        String kirjoittaja = kysyKirjoittaja();
        URL url = kysyUrl();
        ArtikkeliVinkki artikkeliVinkki = new ArtikkeliVinkki(url, otsikko, kirjoittaja);
        app.lisaaVinkki(artikkeliVinkki);
        output.println("Artikkeli lisätty");
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

    private String kysyKanava() {
        while (true) {
            String kanava = getInput("Youtube-kanava");
            if (true) { //validoija.validoiKanava
                return kanava;
            } else {
                output.println("Virhe: Anna kelvollinen Youtube-kanava");
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
