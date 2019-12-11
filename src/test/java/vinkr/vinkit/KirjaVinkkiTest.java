package vinkr.vinkit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.hamcrest.core.*;

import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;

public class KirjaVinkkiTest {

    private static final String NL = System.getProperty("line.separator");
    private KirjaVinkki vinkki;

    @Before
    public void setUp() throws Exception {
        vinkki = new KirjaVinkki("Vinkattavan kirjan nimi", new ArrayList<String>(), "");
    }

    @Test
    public void uudenKirjaVinkinTyyppiOnOikea() {
        assertEquals("kirja", vinkki.getTyyppi());
    }

    @Test
    public void uudenKirjaVinkinLuontiOtsikollaOnOnnistunut() {
        assertEquals("Vinkattavan kirjan nimi", vinkki.getOtsikko());
    }

    @Test
    public void vinkinTunnuksenNoutoOnnistuu() {
        Assert.assertTrue(vinkki.getID().matches("[0-9]{14,16}"));
    }

    @Test
    public void kirjanOtsikonMuuttoOnnistuu() {
        vinkki.setOtsikko("Vinkatun kirjan uusi nimi");
        assertEquals("Vinkatun kirjan uusi nimi", vinkki.getOtsikko());
    }

    @Test
    public void kirjanTekijanLisaysJaNoutoOnnistuu() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        assertEquals("Sukunimi, Etunimi", vinkki.getTekija(1));
    }

    @Test
    public void kirjanTekijanEtunimenNoutoOnnistuu() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        assertEquals("Etunimi", vinkki.getTekijanEtunimi(1));
    }

    @Test
    public void kirjanTekijanSukunimenNoutoOnnistuu() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        assertEquals("Tekijä", vinkki.getTekijanSukunimi(2));
    }

    @Test
    public void kirjanTekijanPoistaminenOnnistuu() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        vinkki.poistaTekija(1);
        assertEquals("Tekijä, Toinen", vinkki.getTekija(1));
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void kirjanLiianMonennenTekijanPoistaminenAiheuttaaPoikkeuksen() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(IsEqual.equalTo("Teoksella on vain 2 tekijä(ä)."));
        vinkki.poistaTekija(3);
    }
    
    @Test
    public void kirjanTekijanKorvaaminenOnnistuu() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        vinkki.lisaaTekija("Tekijä, Kolmas");
        vinkki.korvaaTekija(2, "Tekijä, Neljäs");
        assertEquals("Tekijä, Neljäs", vinkki.getTekija(2));
    }
    
    @Test
    public void kirjanLiianMonennenTekijanKorvaaminenAiheuttaaPoikkeuksen() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(IsEqual.equalTo("Teoksella on vain 2 tekijä(ä)."));
        vinkki.korvaaTekija(3, "Tekijä, Neljäs");
    }
    
    @Test
    public void isbnNumeronLisaysJaNoutoOnnistuu() {
        vinkki.setISBN("1-4346-636-43633");
        assertEquals("1-4346-636-43633", vinkki.getISBN());
    }

    @Test
    public void julkaisupaikanAsetusJaMuotoiluOnnistuu() {
        vinkki.setJulkaisupaikka("Paikka");
        assertEquals("Paikka", vinkki.getJulkaisutiedot());
    }

    @Test
    public void kustantajanAsetusJaMuotoiluOnnistuu() {
        vinkki.setKustantaja("Kustannusyhtiö Oy");
        assertEquals("Kustannusyhtiö Oy", vinkki.getJulkaisutiedot());
    }

    @Test
    public void julkaisuvuodenAsetusJaMuotoiluOnnistuu() {
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("2019", vinkki.getJulkaisutiedot());
    }

    @Test
    public void julkaisupaikanJaKustantajanAsetusJaMuotoiluOnnistuu() {
        vinkki.setJulkaisupaikka("Paikka");
        vinkki.setKustantaja("Kustannusyhtiö Oy");
        assertEquals("Paikka: Kustannusyhtiö Oy", vinkki.getJulkaisutiedot());
    }

    @Test
    public void julkaisupaikanJaVuodenAsetusJaMuotoiluOnnistuu() {
        vinkki.setJulkaisupaikka("Paikka");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Paikka, 2019", vinkki.getJulkaisutiedot());
    }

    @Test
    public void kustantajanJaVuodenAsetusJaMuotoiluOnnistuu() {
        vinkki.setKustantaja("Kustannusyhtiö Oy");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Kustannusyhtiö Oy, 2019", vinkki.getJulkaisutiedot());
    }

    @Test
    public void kaikkienJulkaisutietojenAsetusJaMuotoiluOnnistuu() {
        vinkki.setJulkaisupaikka("Paikka");
        vinkki.setKustantaja("Kustannusyhtiö Oy");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Paikka: Kustannusyhtiö Oy, 2019", vinkki.getJulkaisutiedot());
    }

    @Test
    public void otsikonMerkkijonoesitysToimii() {
        assertEquals("Vinkattavan kirjan nimi", vinkki.toString());
    }

    @Test
    public void yhdenTekijanJaOtsikonMerkkijonoesitysToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        assertEquals("Sukunimi: Vinkattavan kirjan nimi", vinkki.toString());
    }

    @Test
    public void kahdenTekijanJaOtsikonMerkkijonoesitysToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        assertEquals("Sukunimi ja Tekijä: Vinkattavan kirjan nimi", vinkki.toString());
    }
    
    @Test
    public void kolmenTekijanJaOtsikonMerkkijonoesitysToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        vinkki.lisaaTekija("Kirjailija, Esimerkki");
        assertEquals("Sukunimi, Tekijä ja Kirjailija: Vinkattavan kirjan nimi", vinkki.toString());
    }
    
    @Test
    public void otsikonJaVuodenMerkkijonoesitysToimii() {
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Vinkattavan kirjan nimi (2019)", vinkki.toString());
    }

    @Test
    public void taydellinenMerkkijonoesitysToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Sukunimi: Vinkattavan kirjan nimi (2019)", vinkki.toString());
    }

    @Test
    public void taydellinenMerkkijonoesitysUseallaNimellaToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        vinkki.lisaaTekija("Kirjailija, Esimerkki");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Sukunimi, Tekijä ja Kirjailija: Vinkattavan kirjan nimi (2019)", vinkki.toString());
    }
    
    @Test
    public void otsikonTulostusToimii() {
        assertEquals("Tyyppi: Kirja" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "Luettu: " + vinkki.tulostaLukuprosentti() + NL, vinkki.tulosta());
    }

    @Test
    public void yhdenTekijanOtsikonJaISBNnTulostusToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.setISBN("1-4346-636-43633");
        assertEquals("Tyyppi: Kirja" + NL + "Tekijä: Sukunimi, Etunimi" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "ISBN: 1-4346-636-43633" + NL + "Luettu: " + vinkki.tulostaLukuprosentti() + NL, vinkki.tulosta());
    }

    @Test
    public void kahdenTekijanOtsikonJaISBNnTulostusToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        vinkki.setISBN("1-4346-636-43633");
        assertEquals("Tyyppi: Kirja" + NL + "Tekijät: Sukunimi, Etunimi ja Toinen Tekijä" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "ISBN: 1-4346-636-43633" + NL + "Luettu: " + vinkki.tulostaLukuprosentti() + NL, vinkki.tulosta());
    }
    
    @Test
    public void taydellistenTietojenTulostusToimii() {
        vinkki.lisaaTekija("Sukunimi, Etunimi");
        vinkki.lisaaTekija("Tekijä, Toinen");
        vinkki.lisaaTekija("Tekijä, Kolmas");
        vinkki.setISBN("1-4346-636-43633");
        vinkki.setJulkaisupaikka("Paikka");
        vinkki.setKustantaja("Kustannusyhtiö Oy");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Tyyppi: Kirja" + NL + "Tekijät: Sukunimi, Etunimi; Toinen Tekijä ja Kolmas Tekijä" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "Julkaisutiedot: Paikka: Kustannusyhtiö Oy, 2019" + NL + "ISBN: 1-4346-636-43633" + NL + "Luettu: " + vinkki.tulostaLukuprosentti() + NL, vinkki.tulosta());
    }
    
    @Test
    public void getTekijoidenMaaraToimiiKunTekijoitaEiLuotu() {
        KirjaVinkki testiVinkki = new KirjaVinkki("testikirja", null, "");
        assertEquals(0, testiVinkki.getTekijoidenMaara());
    }
}
