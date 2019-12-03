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

    @Before
    public void setUp() throws Exception {
        vinkki = new ArtikkeliVinkki(new URL("https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram"), "Internet celebrity cat Lil Bub has died", "");
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
        SimpleDateFormat muoto = new SimpleDateFormat("dd.MM.yyy");
        vinkki.setJulkaisupvm(muoto.parse("02.12.2019"));
        assertEquals("02.12.2019", muoto.format(vinkki.getJulkaisupvm()));
    }

    @Test
    public void julkaisunURLnAsetusJaMuotoiluOnnistuu() throws Exception {
        vinkki.setUrl(new URL("https://www.theverge.com/circuitbreaker/2019/12/2/20992125/apple-mini-led-ipad-macbook-pro-2020-oled-rumor-kuo"));
        assertEquals("https://www.theverge.com/circuitbreaker/2019/12/2/20992125/apple-mini-led-ipad-macbook-pro-2020-oled-rumor-kuo", vinkki.getUrl().toString());
    }    
    
/*
    @Test
    public void otsikonMerkkijonoesitysToimii() {
        assertEquals("Vinkattavan kirjan nimi", vinkki.toString());
    }

    @Test
    public void tekijanJaOtsikonMerkkijonoesitysToimii() {
        vinkki.setTekija("Sukunimi, Etunimi");
        assertEquals("Sukunimi: Vinkattavan kirjan nimi", vinkki.toString());
    }

    @Test
    public void otsikonJaVuodenMerkkijonoesitysToimii() {
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Vinkattavan kirjan nimi (2019)", vinkki.toString());
    }

    @Test
    public void taydellinenMerkkijonoesitysToimii() {
        vinkki.setTekija("Sukunimi, Etunimi");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Sukunimi: Vinkattavan kirjan nimi (2019)", vinkki.toString());
    }
    
    @Test
    public void otsikonTulostusToimii() {
        assertEquals("Nimeke: Vinkattavan kirjan nimi" + NL, vinkki.tulosta());
    }

    @Test
    public void tekijanOtsikonJaISBNnTulostusToimii() {
        vinkki.setTekija("Sukunimi, Etunimi");
        vinkki.setISBN("1-4346-636-43633");
        assertEquals("Tekijä: Sukunimi, Etunimi" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "ISBN: 1-4346-636-43633" + NL, vinkki.tulosta());
    }

    @Test
    public void taydellistenTietojenTulostusToimii() {
        vinkki.setTekija("Sukunimi, Etunimi");
        vinkki.setISBN("1-4346-636-43633");
        vinkki.setJulkaisupaikka("Paikka");
        vinkki.setKustantaja("Kustannusyhtiö Oy");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Tekijä: Sukunimi, Etunimi" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "Julkaisutiedot: Paikka: Kustannusyhtiö Oy, 2019" + NL + "ISBN: 1-4346-636-43633" + NL, vinkki.tulosta());
    }
    
    */

}
