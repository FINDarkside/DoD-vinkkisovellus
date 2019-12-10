package vinkr;

import java.io.IOException;
import vinkr.vinkit.*;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TextUI {

    public static final String NL = System.getProperty("line.separator");
    private final Vinkr app;
    private final Scanner input;
    private final PrintStream output;
    private final Validoija validoija;
    private final Logo logo;
    private final Tallennus tallennus;

    public TextUI(Vinkr app, InputStream inputStream, OutputStream outputStream, Tallennus tallennus) {
        this.app = app;
        this.input = new Scanner(inputStream);
        this.output = new PrintStream(outputStream);
        this.validoija = new Validoija(app);
        this.logo = new Logo();
        this.tallennus = tallennus;
    }

    public void run() {
        output.println(logo.logoStringiksi());
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
        output.println("  apua: Tulosta tämä lista uudestaan");
        output.println("  lisaa: Lisää uusi lukuvinkki");
        output.println("  listaa: Listaa kaikki lukuvinkit");
        output.println("  avaa: Avaa annetun vinkin sisältämä linkki");
        output.println("  tallenna: Tallenna vinkit");
        output.println("  lopeta: Sulje sovellus");
    }

    private void kasitteleKomento(String komento) {
        switch (komento) {
            case "lisaa":
                lisaaVinkki();
                break;
            case "listaa":
                listaaVinkit();
                break;
            case "avaa":
                avaaLinkki();
            case "apua":
                listaaKomennot();
                break;
            case "tallenna":
                tallenna();
                break;
            default:
                output.println("Komentoa ei tunnistettu");
        }
    }

    private void tulostaVinkkiApukomennosta() {
        output.println();
        output.println("Jos tahdot nähdä kaikki komennot, kirjoita 'apua'");
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
        youtubeVinkki.setJulkaisupvm(kysyPvm());
        app.lisaaVinkki(youtubeVinkki);
        output.println("Video lisätty");
    }

    private void lisaaArtikkeli() {
        URL url = kysyUrl();
        String otsikko = kysyOtsikko();
        String kirjoittaja = kysyKirjoittaja();
        ArtikkeliVinkki artikkeliVinkki = new ArtikkeliVinkki(url, otsikko, kirjoittaja);
        artikkeliVinkki.setJulkaisu(kysyJulkaisu());
        artikkeliVinkki.setJulkaisupvm(kysyPvm());
        app.lisaaVinkki(artikkeliVinkki);
        output.println("Artikkeli lisätty");
    }

    private void lisaaKirja() {
        String isbn = kysyIsbn();
        String otsikko = kysyOtsikko();
        ArrayList<String> kirjoittajat = kysyKirjoittajat();
        KirjaVinkki kirjaVinkki = new KirjaVinkki(otsikko, kirjoittajat, isbn); 
        kirjaVinkki.setJulkaisupaikka(kysyJulkaisupaikka());
        kirjaVinkki.setKustantaja(kysyKustantaja());
        kirjaVinkki.setJulkaisuvuosi(kysyJulkaisuvuosi());
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
            return kanava;
        }
    }
    
    private String kysyJulkaisu() {
        while (true) {
            String julkaisu = getInput("Julkaisu tai sivusto");
            return julkaisu;
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
    
    private ArrayList<String> kysyKirjoittajat() {
        ArrayList<String> kirjoittajat = new ArrayList<>();
        output.println("Syötä kirjoittajat yksi kerrallaan muodossa 'Sukunimi, Etunimi' ja lopuksi tyhjä.");
        int i = 1;
        while (true) {
            String kirjoittaja = getInput(i + ". kirjoittaja");
            if (kirjoittaja.equals("")) {
                return kirjoittajat;
            }
            if (validoija.validoiTekija(kirjoittaja) == true) {
                i++;
                kirjoittajat.add(kirjoittaja);
            } else {
                output.println("Virhe: Syötä kirjoittajan nimi muodossa 'Sukunimi, Etunimi'");
            }
        }
    }
  
    private String kysyJulkaisupaikka() {
        while (true) {
            String julkaisupaikka = getInput("Julkaisupaikka");
            return julkaisupaikka;
        }
    }
    
    private String kysyKustantaja() {
        while (true) {
            String kustantaja = getInput("Kustantaja");
            return kustantaja;
        }
    }
    
    private int kysyJulkaisuvuosi() {
        while (true) {
            String vuosi = getInput("Julkaisuvuosi");
            if (vuosi.equals("")) {
                return 0;
            } else {
                if (validoija.validoiVuosi(vuosi) == true) {
                    return Integer.parseInt(vuosi);
                } else {
                    output.println("Virhe: Anna kelvollinen vuosiluku tai jätä kenttä tyhjäksi");
                }
            }
        }
    }
    
    private Date kysyPvm() {
        SimpleDateFormat pvmMuoto = new SimpleDateFormat("dd.MM.yyyy");
        Date pvmObjekti = null;
        while (true) {
            String pvm = getInput("Julkaisupäivämäärä");
            if (pvm.equals("")) {
                return pvmObjekti;
            } 
            try {
                pvmObjekti = pvmMuoto.parse(pvm);
                return pvmObjekti;
            } catch (ParseException e) {
                output.println("Virhe: Syötä päivämäärä muodossa 'pp.kk.vvvv'");
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
    
    private URL kysyUrl() {
        URL url = null;
        while (true) {
            String osoite = getInput("URL-osoite");
            try {
                url = new URL(osoite);
                return url;
            } catch (MalformedURLException e) {
                output.println("Virhe: Anna kelvollinen URL-osoite");
            }
        }
    }

    private void listaaVinkit() {
        output.println();
        if (app.getVinkit().isEmpty()) {
            output.print("Ei vinkkejä.");
            output.println("");
        } else {
            for (int i = 0; i < app.getVinkit().size(); i++) {
                Vinkki vinkki = app.getVinkit().get(i);
                int numero = i + 1;
                output.println("#" + numero);
                output.println(Varit.varjaa(vinkki.getVari(), vinkki.tulosta()));
            }
        }
    }

    private void avaaLinkki() {
        while (true) {
            String vinkki = getInput("Vinkin numero");
            vinkki = vinkki.replace("#", "");
            if (validoija.validoiLinkki(vinkki) == true) {
                try {
                    app.getVinkit().get(Integer.parseInt(vinkki) - 1).avaaLinkki();
                } catch (IOException | URISyntaxException e) {
                    output.println("Virhe: Linkin avaaminen ei onnistu");
                } catch (Exception e) {
                    output.println("Virhe: Vinkki ei sisällä linkkiä");
                }
                break;
            } else {
                output.println("Virhe: Anna kelvollinen vinkin numero");
            }
        }
    }

    private void tallenna() {
        String json = app.serialisoi();
        try {
            tallennus.tallenna(json);
            output.println("Tietojen tallennus onnistui.");
        } catch (IOException ex) {
            output.println("Virhe: Tallennus epäonnistui");
            System.out.println(ex);
        }
    }

    private String getInput(String name) {
        output.print(name + ": ");
        return input.nextLine();
    }
}
