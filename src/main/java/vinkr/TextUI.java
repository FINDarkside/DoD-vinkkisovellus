package vinkr;

import java.io.IOException;

import vinkr.IO.ISBNTuonti;
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
        output.println(Varit.varjaa(Varit.VAALEANSININEN, logo.logoStringiksi()));
        listaaKomennot();

        while (true) {
            String komento = getInput("Komento");
            if (komento.equals("lopeta")) {
                break;
            }
            kasitteleKomento(komento);
        }
    }

    private void listaaKomennot() {
        output.println("Kaikki käytettävissä olevat komennot:");
        output.println("  apua: Tulosta tämä lista uudestaan");
        output.println("  lisaa: Lisää uusi lukuvinkki");
        output.println("  listaa: Listaa kaikki lukuvinkit");
        output.println("  avaa: Avaa annetun vinkin sisältämä linkki");
        output.println("  lue: Päivitä olemassa olevan vinkin lukuprosenttia");
        output.println("  tallenna: Tallenna vinkit");
        output.println("  lopeta: Sulje sovellus");
    }

    private void kasitteleKomento(String komento) {
        if (komento.equals("lisaa")) {
            lisaaVinkki();
        } else if (komento.equals("listaa")) {
            listaaVinkit();
        } else if (komento.equals("avaa")) {
            avaaLinkki();
        } else if (komento.equals("apua")) {
            listaaKomennot();
        } else if (komento.equals("tallenna")) {
            tallenna();
        } else if (komento.equals("lue")) {
            paivitaProsenttia();
        } else {
            output.println("Komentoa ei tunnistettu");
            tulostaVinkkiApukomennosta();
        }
    }

    private void paivitaProsenttia() {
        while (true) {
            String vinkki = getInput("Vinkin numero");
            vinkki = vinkki.replace("#", "");
            if (validoija.validoiLinkki(vinkki) == true) {
                int prosentti = -1;
                while (!validoija.validoiLukuprosentti(prosentti)) {
                    prosentti = kysyLukuprosentti();
                }
                output.println("Lukuprosentti päivitetty." + NL);
                app.getVinkit().get(Integer.parseInt(vinkki) - 1).setLukuprosentti(prosentti);
                break;
            } else {
                output.println("Virhe: Anna kelvollinen vinkin numero");
            }
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
        youtubeVinkki.setLukuprosentti(kysyLukuprosentti());
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
        artikkeliVinkki.setLukuprosentti(kysyLukuprosentti());
        app.lisaaVinkki(artikkeliVinkki);
        output.println("Artikkeli lisätty");
    }

    private void lisaaKirja() {
        KirjaVinkki kirjaVinkki;
        String isbn = kysyIsbn();
        if (isbn.equals("")) {
            kirjaVinkki = kysyKirjanTiedot(isbn);
            
        } else {
            output.println("Haetaan kirjan tietoja...");
            kirjaVinkki = haeIsbnTiedot(isbn);
        }
        kirjaVinkki.setLukuprosentti(kysyLukuprosentti());
        app.lisaaVinkki(kirjaVinkki);
        output.println("Kirja lisätty");
    }

    private KirjaVinkki kysyKirjanTiedot(String isbn) {
        String otsikko = kysyOtsikko();
        ArrayList<String> kirjoittajat = kysyKirjoittajat();
        KirjaVinkki kirjaVinkki = new KirjaVinkki(otsikko, kirjoittajat, isbn); 
        kirjaVinkki.setJulkaisupaikka(kysyJulkaisupaikka());
        kirjaVinkki.setKustantaja(kysyKustantaja());
        kirjaVinkki.setJulkaisuvuosi(kysyJulkaisuvuosi());
        return kirjaVinkki;
    }
    
    private KirjaVinkki haeIsbnTiedot(String isbn) {
        ISBNTuonti isbnTuonti;
        try {
            isbnTuonti = new ISBNTuonti(validoija);
            isbnTuonti.haeKirja(isbn);
            if (isbnTuonti.getOtsikko().equals("")) {
                output.println("Virhe: ISBN-numerolla ei löytynyt kirjaa; syötä tiedot manuaalisesti.");
                return kysyKirjanTiedot(isbn);
            } else {
                return isbnTuonti.luoKirjaVinkki(isbn);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e);
            output.println("Virhe: Kirjan haku ISBN-numerolla ei onnistunut; syötä tiedot manuaalisesti.");
            return kysyKirjanTiedot(isbn);
        }
    }
    
    private String kysyOtsikko() {
        while (true) {
            String otsikko = getInput("Otsikko");
            if (validoija.validoiOtsikko(otsikko) == true) {
                return otsikko;
            } else {
                output.println("Virhe: Otsikko ei saa olla tyhjä.");
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
                output.println("Virhe: Syötä kirjoittajan nimi muodossa 'Sukunimi, Etunimi'.");
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
                output.println("Virhe: Syötä kirjoittajan nimi muodossa 'Sukunimi, Etunimi'.");
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
                    output.println("Virhe: Anna kelvollinen vuosiluku tai jätä kenttä tyhjäksi.");
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
                output.println("Virhe: Syötä päivämäärä muodossa 'pp.kk.vvvv'.");
            }
        }
    }
    
    private String kysyIsbn() {
        while (true) {
            String isbn = getInput("ISBN");
            if (validoija.validoiIsbn(isbn) == true) {
                return isbn;
            } else {
                output.println("Virhe: Anna kelvollinen ISBN-numero.");
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
                output.println("Virhe: Anna kelvollinen URL-osoite.");
            }
        }
    }

    private int kysyLukuprosentti() {
        int prosentti = -1;
        while (true) {
            try {
                prosentti = Integer.parseInt("0" + getInput("Lukuprosentti"));
            } catch (NumberFormatException e) {
            }
            if (validoija.validoiLukuprosentti(prosentti)) {
                return prosentti;
            } else {
                output.println("Virhe: Anna kelvollinen lukuprosentti (0-100)");
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
                    output.println("Virhe: Linkin avaaminen ei onnistu.");
                } catch (Exception e) {
                    output.println("Virhe: Vinkki ei sisällä linkkiä.");
                }
                break;
            } else {
                output.println("Virhe: Anna kelvollinen vinkin numero");
                break;
            }
        }
    }

    private void tallenna() {
        String json = app.serialisoi();
        try {
            tallennus.tallenna(json);
            output.println("Tietojen tallennus onnistui.");
        } catch (IOException ex) {
            output.println("Virhe: Tallennus epäonnistui.");
            System.out.println(ex);
        }
    }

    private String getInput(String name) {
        output.print(name + ": ");
        return input.nextLine();
    }
}
