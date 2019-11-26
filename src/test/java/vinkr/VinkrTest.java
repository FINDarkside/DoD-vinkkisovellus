package vinkr;

import org.junit.*;
import static org.junit.Assert.*;
import vinkr.vinkit.KirjaVinkki;

public class VinkrTest {

    Vinkr vinkr;

    @Before
    public void setUp() {
        vinkr = new Vinkr();
    }

    @Test
    public void vinkinLisaysOnnistuu() {
        vinkr.lisaaVinkki(new KirjaVinkki("Refactoring", "Fowler, Martin", "0201485672"));
        assertEquals(vinkr.getVinkit().size(), 1);
    }
}
