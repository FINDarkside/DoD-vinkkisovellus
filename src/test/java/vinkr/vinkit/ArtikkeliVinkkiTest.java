package vinkr.vinkit;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;

public class ArtikkeliVinkkiTest {

    private static final String NL = System.getProperty("line.separator");
    private ArtikkeliVinkki vinkki;
    private SimpleDateFormat muoto;

    @Before
    public void setUp() throws Exception {
        vinkki = new ArtikkeliVinkki(new URL("https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram"), "Internet celebrity cat Lil Bub has died", "");
        muoto = new SimpleDateFormat("dd.MM.yyy");
    }

    @Test
    public void uudenArtikkeliVinkinTyyppiOnOikea() {
        assertEquals("artikkeli", vinkki.getTyyppi());
    }

    @Test
    public void uudenArtikkeliVinkinLuontiOtsikollaOnOnnistunut() {
        assertEquals("Internet celebrity cat Lil Bub has died", vinkki.getOtsikko());
    }

    @Test
    public void vinkinTunnuksenNoutoOnnistuu() {
        Assert.assertTrue(vinkki.getID().matches("[0-9]{16}"));
    }

    @Test
    public void artikkelinOtsikonMuuttoOnnistuu() {
        vinkki.setOtsikko("Internet celebrity cat Lil Bub has died - renamed");
        assertEquals("Internet celebrity cat Lil Bub has died - renamed", vinkki.getOtsikko());
    }

    @Test
    public void artikkelinTekijanLisaysJaNoutoOnnistuu() {
        vinkki.setTekija("Lee, Dami");
        assertEquals("Lee, Dami", vinkki.getTekija());
    }

    @Test
    public void artikkelinTekijanEtunimenNoutoOnnistuu() {
        vinkki.setTekija("Lee, Dami");
        assertEquals("Dami", vinkki.getTekijanEtunimi());
    }

    @Test
    public void artikkelinTekijanSukunimenNoutoOnnistuu() {
        vinkki.setTekija("Lee, Dami");
        assertEquals("Lee", vinkki.getTekijanSukunimi());
    }

    @Test
    public void julkaisunAsetusJaMuotoiluOnnistuu() {
        vinkki.setJulkaisu("The Verge");
        assertEquals("The Verge", vinkki.getJulkaisu());
    }

    @Test
    public void julkaisupvmAsetusJaMuotoiluOnnistuu() throws Exception {
        vinkki.setJulkaisupvm(muoto.parse("02.12.2019"));
        assertEquals("02.12.2019", muoto.format(vinkki.getJulkaisupvm()));
    }

    @Test
    public void julkaisunURLnAsetusJaMuotoiluOnnistuu() throws Exception {
        vinkki.setUrl(new URL("https://www.theverge.com/circuitbreaker/2019/12/2/20992125/apple-mini-led-ipad-macbook-pro-2020-oled-rumor-kuo"));
        assertEquals("https://www.theverge.com/circuitbreaker/2019/12/2/20992125/apple-mini-led-ipad-macbook-pro-2020-oled-rumor-kuo", vinkki.getUrl().toString());
    }    
    
    @Test
    public void otsikonJaUrlinMerkkijonoesitysToimii() {
        assertEquals("Internet celebrity cat Lil Bub has died [https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram]", vinkki.toString());
    }

    @Test
    public void taydellinenMerkkijonoesitysToimii() throws Exception {
        vinkki.setJulkaisupvm(muoto.parse("02.12.2019"));
        assertEquals("Internet celebrity cat Lil Bub has died (02.12.2019) [https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram]", vinkki.toString());
    }
    
    @Test
    public void otsikonJaUrlinTulostusToimii() {
        assertEquals("Tyyppi: Artikkeli" + NL + "Otsikko: Internet celebrity cat Lil Bub has died" + NL + "URL: https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram" + NL, vinkki.tulosta());
    }

    @Test
    public void otsikonUrlinJaTekijanTulostusToimii() {
        vinkki.setTekija("Lee, Dami");
        assertEquals("Tyyppi: Artikkeli" + NL + "Otsikko: Internet celebrity cat Lil Bub has died" + NL + "Kirjoittaja: Lee, Dami" + NL + "URL: https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram" + NL, vinkki.tulosta());
    }

    @Test
    public void otsikonUrlinJulkaisunJaTekijanTulostusToimii() {
        vinkki.setTekija("Lee, Dami");
        vinkki.setJulkaisu("The Verge");
        assertEquals("Tyyppi: Artikkeli" + NL + "Otsikko: Internet celebrity cat Lil Bub has died" + NL + "Kirjoittaja: Lee, Dami" + NL + "Lähde: The Verge" + NL + "URL: https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram" + NL, vinkki.tulosta());
    }

    @Test
    public void taydellistenTietojenTulostusToimii() throws Exception {
        vinkki.setTekija("Lee, Dami");
        vinkki.setJulkaisu("The Verge");
        vinkki.setJulkaisupvm(muoto.parse("02.12.2019"));
        assertEquals("Tyyppi: Artikkeli" + NL + "Otsikko: Internet celebrity cat Lil Bub has died" + NL + "Kirjoittaja: Lee, Dami" + NL + "Lähde: The Verge" + NL + "Julkaistu: 02.12.2019" + NL + "URL: https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram" + NL, vinkki.tulosta());
    }

}
