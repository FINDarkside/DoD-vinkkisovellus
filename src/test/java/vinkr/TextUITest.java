package vinkr;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.Timeout;
import static org.mockito.Mockito.*;

import vinkr.vinkit.ArtikkeliVinkki;
import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;
import vinkr.vinkit.YoutubeVinkki;

public class TextUITest {
    
    @Rule
    public Timeout globalTimeout = Timeout.seconds(1); 
    
    public static final String NL = System.getProperty("line.separator");
    Vinkr vinkr;
    Validoija validoija;
    TextUI ui;
    PrintStream uiInput;
    ByteArrayOutputStream uiOutput;
    ArrayList<Vinkki> vinkit;
    Tallennus tallennus;

    @Before
    public void setUp() throws IOException {
        PipedOutputStream uiInputPiped = new PipedOutputStream();
        uiInput = new PrintStream(uiInputPiped, true);
        PipedInputStream input = new PipedInputStream(uiInputPiped);
        uiOutput = new ByteArrayOutputStream();
        vinkit = new ArrayList<>();
        lisaaVinkit();
        vinkr = mock(Vinkr.class);
        when(vinkr.getVinkit()).thenReturn(vinkit);
        validoija = mock(Validoija.class);
        when(validoija.validoiOtsikko(anyString())).thenReturn(true);
        when(validoija.validoiTekija(anyString())).thenReturn(true);
        when(validoija.validoiIsbn(anyString())).thenReturn(true);
        when(validoija.validoiLukuprosentti(anyInt())).thenReturn(true);
        tallennus = mock(Tallennus.class);
        when(vinkr.serialisoi()).thenReturn("{}");
        ui = new TextUI(vinkr, input, uiOutput, tallennus);
    }

    private void lisaaVinkit() throws IOException {
        ArrayList<String> kirjoittajat = new ArrayList<>();
        kirjoittajat.add("Dijkstra, Edsger");
        vinkit.add(new KirjaVinkki("Formal Development of Programs and Proofs", kirjoittajat , "978-0201172379"));
        kirjoittajat.set(0, "Fowler, Martin");
        vinkit.add(new KirjaVinkki("Refactoring", kirjoittajat, "0201485672"));
        vinkit.add(new ArtikkeliVinkki(new URL("https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram"), "Internet celebrity cat Lil Bub has died", ""));
        vinkit.add(new YoutubeVinkki(new URL("https://www.youtube.com/watch?v=9TycLR0TqFA"), "Introduction to Scrum - 7 Minutes", ""));
    }

    private void lisaaMockArtikkeli() {
        ArtikkeliVinkki mockArtikkeli = mock(ArtikkeliVinkki.class);
        vinkit.add(mockArtikkeli);
    }
    
    @Test
    public void lisaaKomentoLisaaKirjanIlmanKustannustietoja() {
        uiInput.print("lisaa\n");
        uiInput.print("kirja\n");
        uiInput.print("013215871X\n");
        uiInput.print("A Discipline of Programming\n");
        uiInput.print("Dijkstra, Edsger\n");
        uiInput.print("\n");
        uiInput.print("\n");
        uiInput.print("\n");
        uiInput.print("\n");
        uiInput.print("\n");
        uiInput.print("lopeta\n");
        uiInput.print("\n");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }
    
    @Test
    public void lisaaKomentoLisaaKirjanKustannustiedoilla() {
        uiInput.print("lisaa\n");
        uiInput.print("kirja\n");
        uiInput.print("013215871X\n");
        uiInput.print("A Discipline of Programming\n");
        uiInput.print("Dijkstra, Edsger\n");
        uiInput.print("\n");
        uiInput.print("Upper Saddle River\n");
        uiInput.print("Prentice Hall\n");
        uiInput.print("1997\n");
        uiInput.print("\n");
        uiInput.print("lopeta\n");
        uiInput.print("\n");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }
    
    @Test
    public void lisaaKomentoLisaaArtikkelin() {
        uiInput.print("lisaa\n");
        uiInput.print("artikkeli\n");
        uiInput.print("https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram\n");
        uiInput.print("Internet celebrity cat Lil Bub has died\n");
        uiInput.print("Lee, Dami\n");
        uiInput.print("The Verge\n");
        uiInput.print("02.12.2019\n");
        uiInput.print("\n");
        uiInput.print("lopeta\n");
        uiInput.print("\n");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }
    
    @Test
    public void lisaaKomentoLisaaYoutubeVideon() {
        uiInput.print("lisaa\n");
        uiInput.print("youtube\n");
        uiInput.print("https://www.youtube.com/watch?v=9TycLR0TqFA\n");
        uiInput.print("Introduction to Scrum - 7 Minutes\n");
        uiInput.print("Uzility\n");
        uiInput.print("26.07.2014\n");
        uiInput.print("\n");
        uiInput.print("lopeta\n");
        uiInput.print("\n");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }

    @Test
    public void listaaKomentoTulostaaVinkit() {
        uiInput.print("listaa\n");
        uiInput.print("lopeta\n");
        uiInput.print("\n");
        ui.run();
        String output = getOutput();
        for (Vinkki vinkki : vinkit) {
            assertTrue(output.contains(vinkki.tulosta()));
        }
    }

    @Test
    public void apuaKomentoListaaKomennot() {
        uiInput.print("apua\n");
        uiInput.print("lopeta\n");
        uiInput.print("\n");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains("apua"));
        assertTrue(output.contains("lisaa"));
        assertTrue(output.contains("listaa"));
        assertTrue(output.contains("lopeta"));
        assertTrue(output.contains("avaa"));
        assertTrue(output.contains("lue"));
        assertTrue(output.contains("tallenna"));
    }

    @Test
    public void linkinAvaaminenToimii() throws Exception {
        lisaaMockArtikkeli();

        uiInput.print("avaa\n");
        uiInput.print("5\n");
        uiInput.print("lopeta\n");
        ui.run();

        String output = getOutput();

        verify(vinkit.get(4)).avaaLinkki();
    }

    @Test
    public void kirjanLinkinAvaaminenValittaaOikein() throws Exception {
        uiInput.print("avaa\n");
        uiInput.print("1\n");
        uiInput.print("lopeta\n");
        ui.run();
        String output = getOutput();
        assertTrue(output.contains("Virhe: Vinkki ei sisällä linkkiä"));
    }

    @Test
    public void tallennaKomentoTallentaaJson() throws IOException {
        uiInput.print("tallenna\n");
        uiInput.print("lopeta\n");
        uiInput.print("\n");
        ui.run();
        verify(vinkr).serialisoi();
        verify(tallennus).tallenna(anyString());
    }

    @Test
    public void lukuprosenttiTulostuuNollassaPunaisella() {
        uiInput.print("listaa\n");
        uiInput.print("lopeta\n");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains(Varit.PUNAINEN));
    }

    @Test
    public void lukuprosenttiKeskenKeltaisella() {
        vinkit.get(0).setLukuprosentti(68);

        uiInput.print("listaa\n");
        uiInput.print("lopeta\n");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains(Varit.KELTAINEN));
    }

    @Test
    public void lukuprosenttiTulostuuSadassaVihrealla() {
        vinkit.get(0).setLukuprosentti(100);

        uiInput.print("listaa\n");
        uiInput.print("lopeta\n");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains(Varit.VIHREA));
    }

    @Test
    public void prosentinPaivitysOnnistuu() {
        uiInput.print("lue\n");
        uiInput.print("1\n");
        uiInput.print("99\n");
        uiInput.print("listaa\n");
        uiInput.print("lopeta\n");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains("99%"));
    }

    @Test
    public void prosentinPaivitysValittaaVirheista() {
        uiInput.print("lue\n");
        uiInput.print("99\n");
        uiInput.print("1\n");
        uiInput.print("111\n");
        uiInput.print("2\n");
        uiInput.print("lopeta\n");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains("Anna kelvollinen vinkin numero"));
        assertTrue(output.contains("Anna kelvollinen lukuprosentti"));
    }

    @Test
    public void vinkinLisaysKasitteleeVirheetOikein() {
        uiInput.print("lisaa\n");
        uiInput.print("enpäs\n");
        uiInput.print("takaisin\n");
        uiInput.print("lopeta\n");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains("Vinkin tyyppiä ei tunnistettu"));
    }

    private String getOutput() {
        return new String(uiOutput.toByteArray());
    }
}
