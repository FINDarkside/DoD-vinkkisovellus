
package vinkr;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import org.junit.After;
import static org.junit.Assert.*;

public class Stepdefs {
    Vinkr app;
    String input;
    ByteArrayOutputStream uiOutput;
    TextUI ui;
    InputStream uiInput;
    String validiUrl;
    String validiOtsikko;
    
    @Before
    public void setup() {
        input = "";
        validiUrl = "http://github.com/";
        validiOtsikko = "TestiOtsikko";
    }
        //GIVENIT
    @Given("komento {string} annetaan ohjelmalle")
    public void komentoValittu(String komento) throws Throwable {
        
        input += komento + "\n";
    }
    
    @Given("uusi kirjavinkki, otsikolla {string}, kirjoittajalla {string} ja jonka ISBN on {string} lisataan")
    public void lisataanJarjestelmaanTiettyKirjavinkki(String otsikko, String kirjoittaja, String isbn) {
        input += "lisaa" + "\n";
        input += "kirja" + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += isbn + "\n";
    }
    
        //WHENIT
    
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
    
    @When("tyyppi {string}, otsikko {string}, kirjoittaja {string} ja ISBN {string} annetaan")
    public void kirjanKirjoittajaOtsikkojaIsbnAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String isbn) {
        
        input += tyyppi + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += isbn + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, virheellinen otsikko {string}, kirjoittaja {string} ja ISBN {string} annetaan")
    public void kirjanKirjoittajaVirheellinenOtsikkojaIsbnAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String isbn) {

        input += tyyppi + "\n";
        input += otsikko + "\n";
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiOtsikko + "\n";
        input += kirjoittaja + "\n";
        input += isbn + "\n";
        input += "" + "\n";
        input += "" + "\n";
        input += "" + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, otsikko {string}, kirjoittaja {string} ja URL {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaUrlAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String url) {

        input += tyyppi + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += url + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, otsikko {string}, kirjoittaja {string} ja virheellinen URL {string} annetaan")
    public void artikkelinKirjoittajaOtsikkojaEpakelpoUrlAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String url) {

        input += tyyppi + "\n";
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += url + "\n";
        
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiUrl + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, virheellinen otsikko {string}, kirjoittaja {string} ja URL {string} annetaan")
    public void artikkelinKirjoittajaEpakelpoOtsikkojaUrlAnnetaan(String tyyppi, String otsikko, String kirjoittaja, String url) {

        input += tyyppi + "\n";
        input += otsikko + "\n";
        
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiOtsikko + "\n";
        
        input += kirjoittaja + "\n";
        input += url + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, URL {string}, otsikko {string} ja kanava {string} annetaan")
    public void videonUrlOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, virheellinen URL {string}, otsikko {string} ja kanava {string} annetaan")
    public void videonEpakelpoUrlOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava) {

        input += tyyppi + "\n";
        input += url + "\n";
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiUrl + "\n";
        input += otsikko + "\n";
        input += kanava + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
    @When("tyyppi {string}, URL {string}, virheellinen otsikko {string} ja kanava {string} annetaan")
    public void videonUrlEpakelpoOtsikkoJaKanavaAnnetaan(String tyyppi, String url, String otsikko, String kanava) {

        input += tyyppi + "\n";
        input += url + "\n";
        input += otsikko + "\n";
        //jotta ohjelma sulkeutuu, asetettava inputtiin myös validi syöte
        input += validiOtsikko + "\n";
        input += kanava + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }
    
        //THENIT
    
    @Then("ohjelmaan tulostuu {string}")
    public void ohjelmaVastaaHalutullaTulosteella(String odotettuTuloste) throws UnsupportedEncodingException {
 
        assertTrue(uiOutput.toString("UTF-8").contains(odotettuTuloste));
    }
    
    @Then("ohjelma vastaa tulosteella, jossa kohdat {string}, {string}, {string} ja {string}")
    public void ohjelmaVastaaHalutullaTulosteellaJossaTietytNeljaKohtaa(String kohta1, String kohta2, String kohta3, String kohta4) throws UnsupportedEncodingException {
        
        assertTrue(uiOutput.toString("UTF-8").contains(kohta1));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta2));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta3));
        assertTrue(uiOutput.toString("UTF-8").contains(kohta4));
    }
    
    // ei vielä käytössä
    @Then("ohjelma reagoi tulosteella {string}")
    public void ohjelmanViimeisinTulostettuRivi(String odotettuTuloste) throws UnsupportedEncodingException {
        String[] tulostetutRivit = uiOutput.toString("UTF-8").split("\n");
    }
    
    private void luoUIjaStreamit() {
        uiInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        uiOutput = new ByteArrayOutputStream();
       
        app = new Vinkr();
        ui = new TextUI(app, uiInput, uiOutput);
        ui.run();
    }
}