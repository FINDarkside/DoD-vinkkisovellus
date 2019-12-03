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

public class YoutubeVinkkiTest {

    private static final String NL = System.getProperty("line.separator");
    private YoutubeVinkki vinkki;

    @Before
    public void setUp() throws Exception {
        vinkki = new YoutubeVinkki(new URL("https://www.youtube.com/watch?v=9TycLR0TqFA"), "Introduction to Scrum - 7 Minutes", "");
    }

    @Test
    public void uudenYoutubeVinkinTyyppiOnOikea() {
        assertEquals("youtube", vinkki.getTyyppi());
    }

    @Test
    public void uudenYoutubeVinkinLuontiOtsikollaOnOnnistunut() {
        assertEquals("Introduction to Scrum - 7 Minutes", vinkki.getOtsikko());
    }

    @Test
    public void vinkinTunnuksenNoutoOnnistuu() {
        Assert.assertTrue(vinkki.getID().matches("[0-9]{16}"));
    }

    @Test
    public void videonOtsikonMuuttoOnnistuu() {
        vinkki.setOtsikko("Introduction to Scrum - 7 Minutes - renamed");
        assertEquals("Introduction to Scrum - 7 Minutes - renamed", vinkki.getOtsikko());
    }

    @Test
    public void videonKanavanLisaysJaNoutoOnnistuu() {
        vinkki.setKanava("Uzility");
        assertEquals("Uzility", vinkki.getKanava());
    }

    @Test
    public void julkaisupvmAsetusJaMuotoiluOnnistuu() throws Exception {
        SimpleDateFormat muoto = new SimpleDateFormat("dd.MM.yyy");
        vinkki.setJulkaisupvm(muoto.parse("26.07.2014"));
        assertEquals("26.07.2014", muoto.format(vinkki.getJulkaisupvm()));
    }

    @Test
    public void julkaisunURLnAsetusJaMuotoiluOnnistuu() throws Exception {
        vinkki.setUrl(new URL("https://www.youtube.com/watch?v=XU0llRltyFM"));
        assertEquals("https://www.youtube.com/watch?v=XU0llRltyFM", vinkki.getUrl().toString());
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
