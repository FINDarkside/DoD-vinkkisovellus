package vinkr;

import org.junit.Test;

import java.util.UUID;
import static org.junit.Assert.*;

public class VaritTest {

    @Test
    public void varjaaminenAsettaaVarin() {
        String testiMerkkijono = UUID.randomUUID().toString();
        testiMerkkijono = Varit.varjaa(Varit.KELTAINEN, testiMerkkijono);
        assertTrue(testiMerkkijono.contains(Varit.KELTAINEN));
    }

    @Test
    public void varjaaminenNollaaVarin() {
        String testiMerkkijono = UUID.randomUUID().toString();
        testiMerkkijono = Varit.varjaa(Varit.VIHREA, testiMerkkijono);
        assertTrue(testiMerkkijono.contains(Varit.NOLLAA));
    }
}
