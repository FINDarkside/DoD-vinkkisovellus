package vinkr;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import vinkr.vinkit.ArtikkeliVinkki;
import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;
import vinkr.vinkit.YoutubeVinkki;

public class ValidoijaTest {
    Vinkr app;
    ArrayList<Vinkki> vinkit;
    Validoija validoija;
    
    @Before
    public void setUp() throws Exception {
        app = mock(Vinkr.class);
        vinkit = new ArrayList<>();
        when(app.getVinkit()).thenReturn(vinkit);
        ArrayList<String> kirjoittajat = new ArrayList<>();
        kirjoittajat.add("Dijkstra, Edsger");
        vinkit.add(new KirjaVinkki("Formal Development of Programs and Proofs", kirjoittajat , "978-0201172379"));
        kirjoittajat.set(0, "Fowler, Martin");
        vinkit.add(new KirjaVinkki("Refactoring", kirjoittajat, "0201485672"));
        vinkit.add(new ArtikkeliVinkki(new URL("https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram"), "Internet celebrity cat Lil Bub has died", ""));
        vinkit.add(new YoutubeVinkki(new URL("https://www.youtube.com/watch?v=9TycLR0TqFA"), "Introduction to Scrum - 7 Minutes", ""));
        validoija = new Validoija(app);
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
    public void tavallinenKaanteinenNimiAksenteillaJaViivallaKelpaa() {
        assertEquals(true, validoija.validoiTekija("Lévi-Strauss, Claude"));
    }
    
    @Test
    public void tavallinenKaanteinenNimiKirjaimillaKelpaa() {
        assertEquals(true, validoija.validoiTekija("Edwards, A. S. G."));
    }
    
    @Test
    public void kaanteinenNimiHeittomerkillaKelpaa() {
        assertEquals(true, validoija.validoiTekija("O'Brien, Patrick"));
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
    
    @Test
    public void negatiivinenVuosiEiKelpaa() {
        assertEquals(false, validoija.validoiVuosi("-200"));
    }
    
    @Test
    public void tulevaisuudenVuosiEiKelpaa() {
        assertEquals(false, validoija.validoiVuosi("2100"));
    }
    
    @Test
    public void vuosiJokaEiOleLukuEiKelpaa() {
        assertEquals(false, validoija.validoiVuosi("k300"));
    }
    
    @Test
    public void lahimenneisyydenVuosiKelpaa() {
        assertEquals(true, validoija.validoiVuosi("1996"));
    }
    
    @Test
    public void eiNumeerinenVinkinNumeroEiKelpaa() {
        assertEquals(false, validoija.validoiLinkki("a"));
    }
    
    @Test
    public void liianPieniVinkinNumeroEiKelpaa() {
        assertEquals(false, validoija.validoiLinkki("0"));
    }
    
    @Test
    public void liianSuuriVinkinNumeroEiKelpaa() {
        assertEquals(false, validoija.validoiLinkki("5"));
    }
    
    @Test
    public void listaanLuuluvaVinkinNumeroKelpaa() {
        assertEquals(true, validoija.validoiLinkki("3"));
    }
    
}
