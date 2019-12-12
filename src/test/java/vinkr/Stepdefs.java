package vinkr;

import com.google.gson.JsonParseException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.Timeout;

public class Stepdefs {

    Vinkr app;
    String input;
    ByteArrayOutputStream uiOutput;
    TextUI ui;
    InputStream uiInput;
    String validiUrl;
    String validiOtsikko;
    boolean debug;
    String nykyOtsikko;

    @Before
    public void setup() {
        input = "";
        validiUrl = "http://github.com/";
        validiOtsikko = "TestiOtsikko";
        debug = false;
        nykyOtsikko = "";
    }
    // GIVENIT

    @Given("komento {string} annetaan ohjelmalle")
    public void komentoValittu(String komento) throws Throwable {

        input += komento + "\n";
    }

    @Given("uusi kirjavinkki, otsikolla {string}, kirjoittajalla {string}, jonka ISBN on {string}, julkaisupaikalla {string}, kustantajalla {string} ja julkaisuvuodella {string} lisataan")
    public void lisataanJarjestelmaanTiettyKirjavinkki(String otsikko, String kirjoittaja, String isbn,
            String julkaisupaikka, String kustantaja, String julkaisuvuosi) {
        input += "lisaa" + "\n";
        input += "kirja" + "\n";
        input += isbn + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += "\n";
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
        input += "\n";
        nykyOtsikko = otsikko;

    }

    @Given("uusi kirjavinkki, otsikolla {string}, kirjoittajalla {string}, jonka ISBN on {string}, julkaisupaikalla {string}, kustantajalla {string}, julkaisuvuodella {string} ja lukuprosentilla {string} lisataan")
    public void lisataanJarjestelmaanTiettyKirjavinkkiLukuprosentilla(String otsikko, String kirjoittaja, String isbn,
            String julkaisupaikka, String kustantaja, String julkaisuvuosi, String lukuprosentti) {
        input += "lisaa" + "\n";
        input += "kirja" + "\n";
        input += isbn + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += "\n";
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
        input += lukuprosentti + "\n";

        nykyOtsikko = otsikko;
    }

    @Given("uusi artikkelivinkki, urlilla {string}, otsikolla {string}, kirjoittajalla {string}, julkaisulla {string} ja julkaisupaivalla {string} lisataan")
    public void lisataanJarjestelmaanTiettyArtikkelivinkki(String url, String otsikko, String kirjoittaja,
            String julkaisu, String julkaisupaiva) {
        input += "lisaa" + "\n";
        input += "artikkeli" + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += "\n";
        nykyOtsikko = otsikko;
    }

    @Given("uusi artikkelivinkki, urlilla {string}, otsikolla {string}, kirjoittajalla {string}, julkaisulla {string}, julkaisupaivalla {string} ja lukuprosentilla {string} lisataan")
    public void lisataanJarjestelmaanTiettyArtikkelivinkkiLukuprosentilla(String url, String otsikko,
            String kirjoittaja, String julkaisu, String julkaisupaiva, String lukuprosentti) {
        input += "lisaa" + "\n";
        input += "artikkeli" + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += lukuprosentti + "\n";

        nykyOtsikko = otsikko;
    }

    @Given("uusi youtubevinkki, urlilla {string}, otsikolla {string}, kanavalla {string} ja julkaisupaivalla {string} lisataan")
    public void lisataanJarjestelmaanTiettyYoutubevinkki(String url, String otsikko, String kanava,
            String julkaisupaiva) {
        input += "lisaa" + "\n";
        input += "youtube" + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaiva + "\n";
        input += "\n";
        nykyOtsikko = otsikko;
    }

    @Given("uusi youtubevinkki, urlilla {string}, otsikolla {string}, kanavalla {string}, julkaisupaivalla {string} ja lukuprosentilla {string} lisataan")
    public void lisataanJarjestelmaanTiettyYoutubevinkkiLukuprosentilla(String url, String otsikko, String kanava,
            String julkaisupaiva, String lukuprosentti) {
        input += "lisaa" + "\n";
        input += "youtube" + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaiva + "\n";
        input += lukuprosentti + "\n";
        nykyOtsikko = otsikko;
    }
    // WHENIT

    @When("listataan kaikki lukuvinkit")
    public void listataanLukuvinkit() {
        input += "listaa" + "\n";
        input += "lopeta" + "\n";
        luoUIjaStreamit();
    }

    @When("komento suoritetaan")
    public void komentoSuoritetaan() throws Throwable {
        input += "lopeta\n";
        luoUIjaStreamit();
    }

    @When("kayttaja valitsee vinkin numero {string}")
    public void valitaanVinkki(String numero) {
        input += numero + "\n";
        input += "\n";

        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, otsikko {string}, kirjoittaja {string}, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko, String kirjoittaja,
            String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) {

        input += tyyppi + "\n";
        input += isbn + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += "\n"; // Koska kirjoittajien syöttäminen lopetetaan antamalla tyhjä rivi, tarvitaan
                       // yksi ylimääräinen rivi
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, otsikko {string}, tyhjä kirjoittaja, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko, String isbn,
            String julkaisupaikka, String kustantaja, String julkaisuvuosi) {

        input += tyyppi + "\n";
        input += isbn + "\n";
        input += otsikko + "\n";
        input += "\n"; // Koska kirjoittajien syöttäminen lopetetaan antamalla tyhjä rivi, tarvitaan
                       // yksi ylimääräinen rivi
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, virheellinen otsikko {string}, kirjoittaja {string}, ISBN {string}, julkaisupaikka {string}, kustantaja {string} ja julkaisuvuosi {string} annetaan")
    public void kirjanKirjoittajaVirheellinenOtsikkojaIsbnjaMuutAnnetaan(String tyyppi, String otsikko,
            String kirjoittaja, String isbn, String julkaisupaikka, String kustantaja, String julkaisuvuosi) {

        input += tyyppi + "\n";
        input += isbn + "\n";
        input += otsikko + "\n";
        // jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiOtsikko + "\n";
        input += kirjoittaja + "\n";
        input += "\n"; // Koska kirjoittajien syöttäminen lopetetaan antamalla tyhjä rivi, tarvitaan
                       // yksi ylimääräinen rivi
        input += julkaisupaikka + "\n";
        input += kustantaja + "\n";
        input += julkaisuvuosi + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaUrljaMuutAnnetaan(String tyyppi, String url, String otsikko,
            String kirjoittaja, String julkaisu, String julkaisupaiva) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, virheellinen URL {string}, otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaEpakelpoUrljaMuutAnnetaan(String tyyppi, String url, String otsikko,
            String kirjoittaja, String julkaisu, String julkaisupaiva) {

        input += tyyppi + "\n";
        input += url + "\n";
        // jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiUrl + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, virheellinen otsikko {string}, kirjoittaja {string}, julkaisu {string} ja julkaisupaiva {string} annetaan")
    public void artikkelinKirjoittajaEpakelpoOtsikkojaUrljaMuutAnnetaan(String tyyppi, String url, String otsikko,
            String kirjoittaja, String julkaisu, String julkaisupaiva) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += validiOtsikko + "\n";
        input += kirjoittaja + "\n";
        input += julkaisu + "\n";
        input += julkaisupaiva + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonUrlOtsikkoJaKanavaAnnetaanJaMuut(String tyyppi, String url, String otsikko, String kanava,
            String julkaisupaivamaara) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaivamaara + "\n";
        input += "\n";
        input += "lopeta" + "\n";
        luoUIjaStreamit();
    }

    @When("tyyppi {string}, virheellinen URL {string}, otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonEpakelpoUrlOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava,
            String julkaisupaivamaara) {

        input += tyyppi + "\n";
        input += url + "\n";
        // jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiUrl + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaivamaara + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }

    @When("tyyppi {string}, URL {string}, virheellinen otsikko {string}, kanava {string} ja julkaisupaivamaara {string} annetaan")
    public void videonUrlEpakelpoOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava,
            String julkaisupaivamaara) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        // jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiOtsikko + "\n";
        input += kanava + "\n";
        input += julkaisupaivamaara + "\n";
        input += "\n";
        input += "lopeta" + "\n";

        luoUIjaStreamit();
    }
    /*
     * @When("kayttaja valitsee vinkin numero {string} ja maarittaa sen lukuprosentiksi {string}"
     * ) public void kayttajaMuuttaaLukuprosentin(String numero, String
     * lukuprosentti) { input += numero + "\n"; input += lukuprosentti + "\n"; input
     * += "\n"; input += "lopeta" + "\n";
     * 
     * luoUIjaStreamit(); }
     */
    // THENIT

    @Then("ohjelmaan tulostuu {string}")
    public void ohjelmaVastaaHalutullaTulosteella(String odotettuTuloste) throws UnsupportedEncodingException {
        if (debug) {
            System.out.println(uiOutput.toString("UTF-8"));
        }
        assertTrue(uiOutput.toString("UTF-8").contains(odotettuTuloste));
    }

    @Then("ohjelma vastaa tulosteella, jossa kohdat {string}, {string}, {string} ja {string}")
    public void ohjelmaVastaaHalutullaTulosteellaJossaTietytNeljaKohtaa(String kohta1, String kohta2, String kohta3,
            String kohta4) throws UnsupportedEncodingException {
        assertTrue(uiOutput.toString("UTF-8").contains(kohta1));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta2));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta3));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta4));
    }

    @Then("ohjelma vastaa tulosteella, jossa kirjan vari {string}")
    public void tulosteessaKirjassaHaluttuVari(String vari) throws UnsupportedEncodingException {
        vari = muunnaAnsiKoodiksi(vari);
        assertTrue(uiOutput.toString("UTF-8").contains(vari));
    }

    @Then("ohjelma vastaa tulosteella, jossa artikkelin vari {string}")
    public void tulosteessaArtikkelissaHaluttuVari(String vari) throws UnsupportedEncodingException {
        vari = muunnaAnsiKoodiksi(vari);
        assertTrue(uiOutput.toString("UTF-8").contains(vari));
    }

    @Then("ohjelma vastaa tulosteella, jossa youtubevideon vari {string}")
    public void tulosteessaVideossaHaluttuVari(String vari) throws UnsupportedEncodingException {
        vari = muunnaAnsiKoodiksi(vari);
        assertTrue(uiOutput.toString("UTF-8").contains(vari));
    }

    // ei vielä käytössä
    @Then("ohjelma reagoi tulosteella {string}")
    public void ohjelmanViimeisinTulostettuRivi(String odotettuTuloste) throws UnsupportedEncodingException {
        String[] outputTaulukkona = muunnaOutputTaulukoksi();
        // lisää koodia tietyn rivin poimimiseen
    }

    @Then("juuri lisatyn kirjavinkin lukuprosentti on listauksessa {string}")
    public void listauksessaOikeaLukuprosentti(String lukuprosentti) throws UnsupportedEncodingException {
        String[] outputTaulukkona = muunnaOutputTaulukoksi();
        int indeksi = etsiTaulukostaKohta(outputTaulukkona, "Luettu:");
        // kts. huomiot kirjavinkin värin etsivässä testissä
        assertTrue(outputTaulukkona[indeksi].contains(lukuprosentti));
    }

    @Then("juuri lisatyn kirjavinkin lukuprosentin vari on listauksessa {string}")
    public void listauksessaKirjallaOikeaLukuprosentinVari(String vari) throws UnsupportedEncodingException {
        vari = muunnaAnsiKoodiksi(vari);
        String[] outputTaulukkona = muunnaOutputTaulukoksi();
        int indeksi = etsiTaulukostaKohta(outputTaulukkona, "Luettu:");
        // kts. huomiot kirjavinkin värin etsivässä testissä
        assertTrue(outputTaulukkona[indeksi].contains(vari));
    }

    @Then("juuri lisatyn artikkelivinkin lukuprosentin vari on listauksessa {string}")
    public void listauksessaArtikkelillaOikeaLukuprosentinVari(String vari) throws UnsupportedEncodingException {
        vari = muunnaAnsiKoodiksi(vari);
        String[] outputTaulukkona = muunnaOutputTaulukoksi();
        int indeksi = etsiTaulukostaKohta(outputTaulukkona, "Luettu:");
        // kts. huomiot kirjavinkin värin etsivässä testissä
        assertTrue(outputTaulukkona[indeksi].contains(vari));
    }

    @Then("juuri lisatyn youtubevinkin lukuprosentin vari on listauksessa {string}")
    public void listauksessaVideollaOikeaLukuprosentinVari(String vari) throws UnsupportedEncodingException {
        vari = muunnaAnsiKoodiksi(vari);
        String[] outputTaulukkona = muunnaOutputTaulukoksi();
        int indeksi = etsiTaulukostaKohta(outputTaulukkona, "Katsottu:");
        // kts. huomiot kirjavinkin värin etsivässä testissä
        assertTrue(outputTaulukkona[indeksi].contains(vari));
    }

    // APUMETODEJA
    private void luoUIjaStreamit() {
        uiInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        uiOutput = new ByteArrayOutputStream();

        app = new Vinkr();
        try {
            ui = new TextUI(app, uiInput, uiOutput, null);
        } catch (IOException ex) {
            throw new RuntimeException(ex.toString());
        }
        ui.run();
        /*System.out.println("*****************");
        System.out.println(uiOutput.toString());
        System.out.println("*****************");*/
    }

    /*
     * private void luoUIjaStreamitjaTallennus() throws URISyntaxException{
     * 
     * String currentFolder = new
     * File(TextUI.class.getProtectionDomain().getCodeSource().getLocation().toURI()
     * ).getParent(); Path savePath = Paths.get(currentFolder,
     * "vinkitCucumberTEST.json"); Tallennus tallennus = new Tallennus(savePath);
     * app = new Vinkr(); if (Files.exists(savePath)) { String json; try { json =
     * tallennus.lataa(); app = Vinkr.deserialisoi(json); } catch (IOException |
     * JsonParseException ex) { System.out.println("Vinkkien lataus tiedostosta " +
     * savePath + " epäonnistui."); return; } } else { System.out.println(savePath +
     * " doesn't exist"); }
     * 
     * uiInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
     * uiOutput = new ByteArrayOutputStream(); ui = new TextUI(app, uiInput,
     * uiOutput, tallennus); ui.run(); }
     */
    private void tulostaKonsoliinTanhetkinenInput() {
        System.out.println("*******");
        System.out.println(input);
        System.out.println("*******");
    }

    private String muunnaAnsiKoodiksi(String vari) {
        if (vari.equals("sininen")) {
            return Varit.SININEN;
        }
        if (vari.equals("sinivihrea")) {
            return Varit.SINIVIHREA;
        }
        if (vari.equals("violetti")) {
            return Varit.VIOLETTI;
        }
        if (vari.equals("keltainen")) {
            return Varit.KELTAINEN;
        }
        if (vari.equals("vihrea")) {
            return Varit.VIHREA;
        }
        if (vari.equals("punainen")) {
            return Varit.PUNAINEN;
        }
        return null;
    }

    private String[] muunnaOutputTaulukoksi() throws UnsupportedEncodingException {
        return uiOutput.toString("UTF-8").split("\n");
    }

    private int etsiTaulukostaKohta(String[] taulukko, String haluttuKohta) {

        for (int i = 0; i < taulukko.length; i++) {
            if (taulukko[i].contains(haluttuKohta)) {
                return i;
            }
        }
        return -1;
    }
}
