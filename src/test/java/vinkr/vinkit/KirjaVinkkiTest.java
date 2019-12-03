package vinkr.vinkit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;

public class KirjaVinkkiTest {

    private static final String NL = System.getProperty("line.separator");
    private KirjaVinkki vinkki;

    @Before
    public void setUp() throws Exception {
        vinkki = new KirjaVinkki("Vinkattavan kirjan nimi", "", "");
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
        vinkki.setTekija("Sukunimi, Etunimi");
        assertEquals("Sukunimi, Etunimi", vinkki.getTekija());
    }

    @Test
    public void kirjanTekijanEtunimenNoutoOnnistuu() {
        vinkki.setTekija("Sukunimi, Etunimi");
        assertEquals("Etunimi", vinkki.getTekijanEtunimi());
    }

    @Test
    public void kirjanTekijanSukunimenNoutoOnnistuu() {
        vinkki.setTekija("Sukunimi, Etunimi");
        assertEquals("Sukunimi", vinkki.getTekijanSukunimi());
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
        assertEquals("Tyyppi: Kirja" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL, vinkki.tulosta());
    }

    @Test
    public void tekijanOtsikonJaISBNnTulostusToimii() {
        vinkki.setTekija("Sukunimi, Etunimi");
        vinkki.setISBN("1-4346-636-43633");
        assertEquals("Tyyppi: Kirja" + NL + "Tekijä: Sukunimi, Etunimi" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "ISBN: 1-4346-636-43633" + NL, vinkki.tulosta());
    }

    @Test
    public void taydellistenTietojenTulostusToimii() {
        vinkki.setTekija("Sukunimi, Etunimi");
        vinkki.setISBN("1-4346-636-43633");
        vinkki.setJulkaisupaikka("Paikka");
        vinkki.setKustantaja("Kustannusyhtiö Oy");
        vinkki.setJulkaisuvuosi(2019);
        assertEquals("Tyyppi: Kirja" + NL + "Tekijä: Sukunimi, Etunimi" + NL + "Nimeke: Vinkattavan kirjan nimi" + NL + "Julkaisutiedot: Paikka: Kustannusyhtiö Oy, 2019" + NL + "ISBN: 1-4346-636-43633" + NL, vinkki.tulosta());
    }

}
