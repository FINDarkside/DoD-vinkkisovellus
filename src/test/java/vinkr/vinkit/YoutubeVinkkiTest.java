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
    private SimpleDateFormat muoto;

    @Before
    public void setUp() throws Exception {
        vinkki = new YoutubeVinkki(new URL("https://www.youtube.com/watch?v=9TycLR0TqFA"), "Introduction to Scrum - 7 Minutes", "");
        muoto = new SimpleDateFormat("dd.MM.yyy");
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
        Assert.assertTrue(vinkki.getID().matches("[0-9]{14,16}"));
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
    
    @Test
    public void otsikonJaUrlinMerkkijonoesitysToimii() {
        assertEquals("Introduction to Scrum - 7 Minutes [https://www.youtube.com/watch?v=9TycLR0TqFA]", vinkki.toString());
    }

    @Test
    public void taydellinenMerkkijonoesitysToimii() throws Exception {
        vinkki.setJulkaisupvm(muoto.parse("26.07.2014"));
        assertEquals("Introduction to Scrum - 7 Minutes (26.07.2014) [https://www.youtube.com/watch?v=9TycLR0TqFA]", vinkki.toString());
    }
    
    @Test
    public void otsikonJaUrlinTulostusToimii() {
        assertEquals("Tyyppi: YouTube-video" + NL + "Otsikko: Introduction to Scrum - 7 Minutes" + NL + "URL: https://www.youtube.com/watch?v=9TycLR0TqFA" + NL + "Katsottu: " + vinkki.tulostaLukuprosentti() + NL, vinkki.tulosta());
    }

    @Test
    public void otsikonUrlinJaKanavanTulostusToimii() {
        vinkki.setKanava("Uzility");
        assertEquals("Tyyppi: YouTube-video" + NL + "Otsikko: Introduction to Scrum - 7 Minutes" + NL + "Kanava: Uzility" + NL + "URL: https://www.youtube.com/watch?v=9TycLR0TqFA" + NL + "Katsottu: " + vinkki.tulostaLukuprosentti() + NL, vinkki.tulosta());
    }

    @Test
    public void taydellistenTietojenTulostusToimii() throws Exception {
        vinkki.setKanava("Uzility");
        vinkki.setJulkaisupvm(muoto.parse("26.07.2014"));
        assertEquals("Tyyppi: YouTube-video" + NL + "Otsikko: Introduction to Scrum - 7 Minutes" + NL + "Kanava: Uzility" + NL + "Julkaistu: 26.07.2014" + NL + "URL: https://www.youtube.com/watch?v=9TycLR0TqFA" + NL + "Katsottu: " + vinkki.tulostaLukuprosentti() + NL, vinkki.tulosta());
    }

}
