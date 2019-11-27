package vinkr;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidoijaTest {

    Validoija validoija;
    
    @Before
    public void setUp() throws Exception {
        validoija = new Validoija();
    }

    @Test
    public void tyhjaOtsikkoEiKelpaa() {
        assertEquals(false, validoija.validoiOtsikko(""));
    }

    @Test
    public void tekstiaSisaltavaOtsikkoKelpaa() {
        assertEquals(true, validoija.validoiOtsikko("Variation across speech and writing"));
    }
    
    @Test
    public void tyhjaNimiKelpaa() {
        assertEquals(true, validoija.validoiTekija(""));
    }
    
    @Test
    public void tavallinenKaanteinenNimiKelpaa() {
        assertEquals(true, validoija.validoiTekija("Biber, Douglas"));
    }
    
    @Test
    public void tavallinenKaanteinenNimiSkandeillaKelpaa() {
        assertEquals(true, validoija.validoiTekija("Sisättö, Vesa"));
    }
    
    @Test
    public void tavallinenKaanteinenNimiKirjaimillaKelpaa() {
        assertEquals(true, validoija.validoiTekija("Edwards, A. S. G."));
    }
    
    @Test
    public void hallitsijanNimiKelpaa() {
        assertEquals(true, validoija.validoiTekija("Edward III"));
    }
    
    @Test
    public void yksiosainenNimiKelpaa() {
        assertEquals(true, validoija.validoiTekija("Aristoteles"));
    }
    
    @Test
    public void tavallinenNimiEiKelpaa() {
        assertEquals(false, validoija.validoiTekija("Douglas Biber"));
    }
    
    @Test
    public void tavallinenNimiKirjaimillaEiKelpaa() {
        assertEquals(false, validoija.validoiTekija("A. S. G. Edwards"));
    }
    
    @Test
    public void vaaranMittainenIsbnEiKelpaa() {
        assertEquals(false, validoija.validoiIsbn("356-34577-345345"));
    }
    
    @Test
    public void vaariaMerkkejaSisaltavaIsbnEiKelpaa() {
        assertEquals(false, validoija.validoiIsbn("356-34577-345PS"));
    }
    
    @Test
    public void vaaraUusiIsbnTarkistusnumeroEiKelpaa() {
        assertEquals(false, validoija.validoiIsbn("356-34577-3454-5"));
    }
    
    @Test
    public void vaaraVanhaIsbnTarkistusnumeroEiKelpaa() {
        assertEquals(false, validoija.validoiIsbn("356-373454-5"));
    }
    
    @Test
    public void oikeaVanhaIsbnKelpaa() {
        assertEquals(true, validoija.validoiIsbn("0-19-852663-6"));
    }
    
    @Test
    public void oikeaVanhaIsbnJossaXKelpaa() {
        assertEquals(true, validoija.validoiIsbn("0-8044-2957-X"));
    }
    
    @Test
    public void oikeaUusiIsbnKelpaa() {
        assertEquals(true, validoija.validoiIsbn("978-1-86197-876-9"));
    }
    
    @Test
    public void tyhjaIsbnKelpaa() {
        assertEquals(true, validoija.validoiIsbn(""));
    }
    
}
