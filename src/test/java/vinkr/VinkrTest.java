package vinkr;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import vinkr.vinkit.*;

public class VinkrTest {

    Vinkr vinkr;

    @Before
    public void setUp() {
        vinkr = new Vinkr();
    }

    @Test
    public void vinkinLisaysOnnistuu() {
        ArrayList<String> kirjoittajat = new ArrayList<>();
        kirjoittajat.add("Fowler, Martin");
        vinkr.lisaaVinkki(new KirjaVinkki("Refactoring", kirjoittajat, "0201485672"));
        assertEquals(vinkr.getVinkit().size(), 1);
    }

    @Test
    public void serialisointiJaDeserializointiToimii() throws MalformedURLException {
        vinkr.lisaaVinkki(new ArtikkeliVinkki(new URL("https://stackoverflow.com/"), "StackOverflow", "Joku"));
        ArrayList<String> kirjoittajat = new ArrayList<>();
        kirjoittajat.add("Fowler, Martin");
        vinkr.lisaaVinkki(new KirjaVinkki("Refactoring", kirjoittajat, "0201485672"));
        vinkr.lisaaVinkki(new YoutubeVinkki(new URL("https://www.youtube.com/watch?v=P_nj6wW6Gsc"), "Kerbal Space Program 2 Cinematic Announce Trailer", "Kerbal Space Program"));
        String json = vinkr.serialisoi();
        Vinkr vinkr2 = Vinkr.deserialisoi(json);
        List<Vinkki> vinkit = vinkr2.getVinkit();
        assertEquals(vinkit.get(0).toString(), vinkr.getVinkit().get(0).toString());
        assertEquals(vinkit.get(1).toString(), vinkr.getVinkit().get(1).toString());
        assertEquals(vinkit.get(2).toString(), vinkr.getVinkit().get(2).toString());

    }
}
