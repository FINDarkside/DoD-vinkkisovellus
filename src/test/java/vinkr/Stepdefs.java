package vinkr;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class Stepdefs {

    String output;
    String validiUrl;
    String validiOtsikko;
    PrintStream input;
    PipedInputStream inputStream;

    Validoija validoija;

    @Before
    public void setup() throws IOException {
        validiUrl = "http://github.com/";
        validiOtsikko = "TestiOtsikko";
        PipedOutputStream uiInputPiped = new PipedOutputStream();
        input = new PrintStream(uiInputPiped, true);
        inputStream = new PipedInputStream(uiInputPiped);
    }
    //GIVENIT

    @Given("komento {string} annetaan ohjelmalle")
    public void komentoValittu(String komento) throws Throwable {
        input.println(komento);
    }

    @Given("uusi kirjavinkki, otsikolla {string}, kirjoittajalla {string}, jonka ISBN on {string}, julkaisupaikalla {string}, kustantajalla {string} ja julkaisuvuodella {string} lisataan")
    public void lisataanJarjestelmaanTiettyKirjavinkki(String otsikko, String kirjoittaja, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) {
        input.println("lisaa");
        input.println("kirja");
        input.println(isbn);
        input.println(otsikko);
        input.println(kirjoittaja);
        input.println("");
        input.println(julkaisupaikka);
        input.println(kustantaja);
        input.println(julkaisuvuosi);
        input.println("lopeta");
    }

    /*
    @Given("uusi artikkelivinkki, urlilla {string}, otsikolla {string}, kirjoittajalla {string}, julkaisulla {string} ja julkaisupaivalla {string} annetaan")
    public void lisataanJarjestelmaanTiettyArtikkelivinkki(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) {
        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += "lopeta" + "\n";
    }
        //WHENIT
     */
    @When("listataan kaikki lukuvinkit")
    public void listataanLukuvinkit() throws IOException {
        input.println("lisaa");
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("komento suoritetaan")
    public void komentoSuoritetaan() throws Throwable {
        input.println("lopeta");
        luoUIjaStreamit();
    }

    /*
    @When("kayttaja valitsee vinkin numero {string}")
    public void valitaanVinkki(String numero) {
        input += numero + "\n";
        input += "1" + "\n";
        
        input += "lopeta" + "\n";
        luoUIjaStreamit();
    }
     */
    @When("tyyppi {string}, otsikko {string}, kirjoittaja {string}, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) throws IOException {
        input.println(tyyppi);
        input.println(isbn);
        input.println(otsikko);
        input.println(kirjoittaja);
        input.println("");
        input.println(julkaisupaikka);
        input.println(kustantaja);
        input.println(julkaisuvuosi);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, otsikko {string}, tyhjä kirjoittaja, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) throws IOException {
        input.println(tyyppi);
        input.println(isbn);
        input.println(otsikko);
        input.println("");
        input.println(julkaisupaikka);
        input.println(kustantaja);
        input.println(julkaisuvuosi);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, virheellinen otsikko {string}, kirjoittaja {string}, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaVirheellinenOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) throws IOException {
        input.println(tyyppi);
        input.println(isbn);
        input.println(otsikko);
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input.println(validiOtsikko);
        input.println("");
        input.println(julkaisupaikka);
        input.println(kustantaja);
        input.println(julkaisuvuosi);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaUrljaMuutAnnetaan(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) throws IOException {
        input.println(tyyppi);
        input.println(url);
        input.println(otsikko);
        input.println(kirjoittaja);
        input.println(julkaisu);
        input.println(julkaisupaiva);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, virheellinen URL {string}, otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaEpakelpoUrljaMuutAnnetaan(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) throws IOException {
        input.println(tyyppi);
        input.println(url);
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte´
        input.println(validiUrl);
        input.println(otsikko);
        input.println(kirjoittaja);
        input.println(julkaisu);
        input.println(julkaisupaiva);
        input.println("lopeta");

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, virheellinen otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaEpakelpoOtsikkojaUrljaMuutAnnetaan(String tyyppi, String url, String otsikko, String kirjoittaja, String julkaisu, String julkaisupaiva) throws IOException {
        input.println(tyyppi);
        input.println(url);
        input.println(otsikko);
        input.println(validiOtsikko);
        input.println(kirjoittaja);
        input.println(julkaisu);
        input.println(julkaisupaiva);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonUrlOtsikkoJaKanavaAnnetaanJaMuut(String tyyppi, String url, String otsikko, String kanava, String julkaisupaivamaara) throws IOException {
        input.println(tyyppi);
        input.println(url);
        input.println(otsikko);
        input.println(kanava);
        input.println(julkaisupaivamaara);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, virheellinen URL {string}, otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonEpakelpoUrlOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava, String julkaisupaivamaara) throws IOException {
        input.println(tyyppi);
        input.println(url);
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input.println(validiUrl);
        input.println(otsikko);
        input.println(kanava);
        input.println(julkaisupaivamaara);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, virheellinen otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonUrlEpakelpoOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava, String julkaisupaivamaara) throws IOException {
        input.println(tyyppi);
        input.println(url);
        input.println(otsikko);
        // jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input.println(validiOtsikko);
        input.println(kanava);
        input.println(julkaisupaivamaara);
        input.println("lopeta");
        luoUIjaStreamit();
    }

    //THENIT
    @Then("ohjelmaan tulostuu {string}")
    public void ohjelmaVastaaHalutullaTulosteella(String odotettuTuloste) throws UnsupportedEncodingException {
        assertTrue(output.contains(odotettuTuloste));
    }

    @Then("ohjelma vastaa tulosteella, jossa kohdat {string}, {string}, {string} ja {string}")
    public void ohjelmaVastaaHalutullaTulosteellaJossaTietytNeljaKohtaa(String kohta1, String kohta2, String kohta3, String kohta4) throws UnsupportedEncodingException {
        assertTrue(output.contains(kohta1));
        assertTrue(output.contains(kohta2));
        assertTrue(output.contains(kohta3));
        assertTrue(output.contains(kohta4));
    }

    // ei vielä käytössä
    @Then("ohjelma reagoi tulosteella {string}")
    public void ohjelmanViimeisinTulostettuRivi(String odotettuTuloste) throws UnsupportedEncodingException {
        String[] tulostetutRivit = output.split("\n");
    }

    private void luoUIjaStreamit() throws UnsupportedEncodingException, IOException {
        ByteArrayOutputStream uiOutput = new ByteArrayOutputStream();

        Vinkr app = new Vinkr();
        TextUI ui;
        ui = new TextUI(app, inputStream, uiOutput, mock(Tallennus.class));
        ui.run();
        output = uiOutput.toString();
        System.out.println("---- OUTPUT ----");
        System.out.println(output);
        System.out.println("-- END OUTPUT --");
    }
}
