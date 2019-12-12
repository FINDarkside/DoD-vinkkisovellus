package vinkr.io;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.json.JSONObject;

import vinkr.Tallennus;
import vinkr.TextUI;
import vinkr.Validoija;
import vinkr.Vinkr;
import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;

public class ISBNTuontiTest {

    public static final String NL = System.getProperty("line.separator");
    Vinkr vinkr;
    Validoija validoija;
    TextUI ui;
    PrintStream uiInput;
    ByteArrayOutputStream uiOutput;
    ArrayList<Vinkki> vinkit;
    Tallennus tallennus;
    JSONObject testikirjaJson;
    ISBNTuonti isbnTuonti;

    @Before
    public void setUp() throws IOException {
        PipedOutputStream uiInputPiped = new PipedOutputStream();
        uiInput = new PrintStream(uiInputPiped, true);
        PipedInputStream input = new PipedInputStream(uiInputPiped);
        uiOutput = new ByteArrayOutputStream();
        vinkr = mock(Vinkr.class);
        validoija = mock(Validoija.class);
        when(validoija.validoiOtsikko(anyString())).thenReturn(true);
        when(validoija.validoiTekija(anyString())).thenReturn(true);
        when(validoija.validoiIsbn(anyString())).thenReturn(true);
        ui = new TextUI(vinkr, input, uiOutput, tallennus);
        testikirjaJson = new JSONObject("{\"ISBN:0195083644\": {\"publishers\": [{\"name\": \"Oxford University Press\"}], \"pagination\": \"x, 385 p. ;\", \"identifiers\": {\"lccn\": [\"92044536\"], \"openlibrary\": [\"OL1739203M\"], \"isbn_10\": [\"0195083644\"], \"librarything\": [\"8863597\"], \"goodreads\": [\"1666591\"]}, \"classifications\": {\"dewey_decimal_class\": [\"306.4/4\"], \"lc_classifications\": [\"P302.815 .S65 1994\"]}, \"title\": \"Sociolinguistic perspectives on register\", \"url\": \"https://openlibrary.org/books/OL1739203M/Sociolinguistic_perspectives_on_register\", \"notes\": \"Includes bibliographical references.\", \"number_of_pages\": 385, \"cover\": {\"small\": \"https://covers.openlibrary.org/b/id/1124918-S.jpg\", \"large\": \"https://covers.openlibrary.org/b/id/1124918-L.jpg\", \"medium\": \"https://covers.openlibrary.org/b/id/1124918-M.jpg\"}, \"subjects\": [{\"url\": \"https://openlibrary.org/subjects/sociolinguistics\", \"name\": \"Sociolinguistics\"}, {\"url\": \"https://openlibrary.org/subjects/register_(linguistics)\", \"name\": \"Register (Linguistics)\"}], \"publish_date\": \"1994\", \"key\": \"/books/OL1739203M\", \"authors\": [{\"url\": \"https://openlibrary.org/authors/OL388813A/Douglas_Biber\", \"name\": \"Douglas Biber\"}, {\"url\": \"https://openlibrary.org/authors/OL244279A/Edward_Finegan\", \"name\": \"Edward Finegan\"}], \"by_statement\": \"edited by Douglas Biber, Edward Finegan.\", \"publish_places\": [{\"name\": \"New York\"}, {\"name\": \"Oxford\"}]}}").getJSONObject("ISBN:0195083644");
        isbnTuonti = new ISBNTuonti(validoija, ui);
        isbnTuonti.haeKirja("0195083644");
    }

    @Test
    public void kirjanTietojenHakuToimii() throws Exception {
        isbnTuonti.haeKirja("9514583469");
        assertEquals("Medieval embryology in the vernacular", isbnTuonti.getOtsikko());
    }
    
    @Test
    public void kirjaVinkinLuontiToimii() throws Exception {
        KirjaVinkki kirjaVinkki = isbnTuonti.luoKirjaVinkki();
        assertEquals("Biber, Douglas", kirjaVinkki.getTekija(1));
    }
    
    
}
