package vinkr.io;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.Mockito;
import org.json.JSONObject;

import vinkr.Validoija;

public class ISBNTuontiTest {

    public static final String NL = System.getProperty("line.separator");
    ISBNTuonti isbnTuonti;
    Validoija validoija;
    ISBNTuontiUI tuontiUI;
    JSONObject testikirjaJson;

    @Before
    public void setUp() throws Exception {
        testikirjaJson = new JSONObject("{\"ISBN:0195083644\": {\"publishers\": [{\"name\": \"Oxford University Press\"}], \"pagination\": \"x, 385 p. ;\", \"identifiers\": {\"lccn\": [\"92044536\"], \"openlibrary\": [\"OL1739203M\"], \"isbn_10\": [\"0195083644\"], \"librarything\": [\"8863597\"], \"goodreads\": [\"1666591\"]}, \"classifications\": {\"dewey_decimal_class\": [\"306.4/4\"], \"lc_classifications\": [\"P302.815 .S65 1994\"]}, \"title\": \"Sociolinguistic perspectives on register\", \"url\": \"https://openlibrary.org/books/OL1739203M/Sociolinguistic_perspectives_on_register\", \"notes\": \"Includes bibliographical references.\", \"number_of_pages\": 385, \"cover\": {\"small\": \"https://covers.openlibrary.org/b/id/1124918-S.jpg\", \"large\": \"https://covers.openlibrary.org/b/id/1124918-L.jpg\", \"medium\": \"https://covers.openlibrary.org/b/id/1124918-M.jpg\"}, \"subjects\": [{\"url\": \"https://openlibrary.org/subjects/sociolinguistics\", \"name\": \"Sociolinguistics\"}, {\"url\": \"https://openlibrary.org/subjects/register_(linguistics)\", \"name\": \"Register (Linguistics)\"}], \"publish_date\": \"1994\", \"key\": \"/books/OL1739203M\", \"authors\": [{\"url\": \"https://openlibrary.org/authors/OL388813A/Douglas_Biber\", \"name\": \"Douglas Biber\"}, {\"url\": \"https://openlibrary.org/authors/OL244279A/Edward_Finegan\", \"name\": \"Edward Finegan\"}], \"by_statement\": \"edited by Douglas Biber, Edward Finegan.\", \"publish_places\": [{\"name\": \"New York\"}, {\"name\": \"Oxford\"}]}}").getJSONObject("ISBN:0195083644");
        //isbnTuonti = mock(ISBNTuonti.class);
        validoija = mock(Validoija.class);
        when(validoija.validoiOtsikko(anyString())).thenReturn(true);
        when(validoija.validoiTekija(anyString())).thenReturn(true);
        when(validoija.validoiVuosi(anyString())).thenReturn(true);
        isbnTuonti = new ISBNTuonti(validoija);
        ISBNTuonti isbnTuontiMock = Mockito.spy(isbnTuonti);
        when(isbnTuontiMock.haeKirjanTiedot()).thenReturn(testikirjaJson);
        when(isbnTuontiMock.haeViafId("http://www.viaf.org/viaf/AutoSuggest?query=douglas+biber")).thenReturn("85093260");
        when(isbnTuontiMock.haeViafId("http://www.viaf.org/viaf/AutoSuggest?query=edward+finegan")).thenReturn("51790592");
        when(isbnTuontiMock.haeViafNimi("http://www.viaf.org/viaf/85093260/viaf.json")).thenReturn("Biber, Douglas");
        when(isbnTuontiMock.haeViafNimi("http://www.viaf.org/viaf/51790592/viaf.json")).thenReturn("Finegan, Edward");
    }

    
    /*
    
    @Test
    public void kirjaVinkinHakuToimii() {
        isbnTuontiMock.luoKirjaVinkki("0195083644");
        assertEquals("Sociolinguistic perspectives on register", isbnTuonti.getOtsikko());
    }
    */
}
