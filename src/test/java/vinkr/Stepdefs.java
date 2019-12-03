
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
    
    @Before
    public void setup() {
        input = "";  
    }
    
    @Given("komento {string} annetaan ohjelmalle")
    public void komentoValittu(String komento) throws Throwable {
        
        input += komento + "\n";
    }
    
    @When("komento suoritetaan")
    public void komentoSuoritetaan() throws Throwable {
        input += "lopeta\n";
        luoUIjaStreamit();
    }
    
    @When("otsikko {string}, kirjoittaja {string} ja ISBN {string} annetaan")
    public void kirjoittajaOtsikkojaISBNannetaan(String otsikko, String kirjoittaja, String isbn) {
        
        input += otsikko + "\n";
        input += kirjoittaja + "\n";
        input += isbn + "\n";
        input += "lopeta" + "\n";
        
        luoUIjaStreamit();
    }    
    
    @Then("ohjelmaan tulostuu {string}")
    public void ohjelmaVastaaHalutullaTulosteella(String odotettuTuloste) throws UnsupportedEncodingException {
 
        assertTrue(uiOutput.toString("UTF-8").contains(odotettuTuloste));
    }  
    
    private void luoUIjaStreamit() {
        uiInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        uiOutput = new ByteArrayOutputStream();
       
        app = new Vinkr();
        ui = new TextUI(app, uiInput, uiOutput);
        ui.run();
    }
}