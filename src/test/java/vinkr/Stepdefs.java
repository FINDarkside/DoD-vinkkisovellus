
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
    public void komentoLisaaValittu(String komento) throws Throwable {
        
        input += komento + "\n";
    }
    
    @When("kirjoittaja {string}, otsikko {string} ja ISBN {string} annetaan")
    public void kirjoittajaOtsikkojaISBNannetaan(String kirjoittaja, String otsikko, String ISBN) {
        
       input += kirjoittaja + "\n";
       input += otsikko + "\n";
       input += ISBN + "\n";
       input += "lopeta\n";

       uiInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
       uiOutput = new ByteArrayOutputStream();
       
       app = new Vinkr();
       ui = new TextUI(app, uiInput, uiOutput);
       ui.run();
    }    
    
    @Then("ohjelma vastaa tulosteella {string}")
    public void ohjelmaVastaaHalutullaTulosteella (String odotettuTuloste) throws UnsupportedEncodingException {
        
        assertTrue(uiOutput.toString("UTF-8").contains(odotettuTuloste));
    }  

}