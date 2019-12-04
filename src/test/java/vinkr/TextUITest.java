package vinkr;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.Timeout;
import static org.mockito.Mockito.*;

import vinkr.vinkit.ArtikkeliVinkki;
import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;
import vinkr.vinkit.YoutubeVinkki;

public class TextUITest {
    
    Vinkr vinkr;
    Validoija validoija;
    TextUI ui;
    PrintStream uiInput;
    ByteArrayOutputStream uiOutput;
    ArrayList<Vinkki> vinkit;
    
    @Before
    public void setUp() throws IOException {
        PipedOutputStream uiInputPiped = new PipedOutputStream();
        uiInput = new PrintStream(uiInputPiped, true);
        PipedInputStream input = new PipedInputStream(uiInputPiped);
        
        uiOutput = new ByteArrayOutputStream();
        
        vinkit = new ArrayList<>();
        vinkit.add(new KirjaVinkki("Formal Development of Programs and Proofs", "Dijkstra, Edsger", "978-0201172379"));
        vinkit.add(new KirjaVinkki("Refactoring", "Fowler, Martin", "0201485672"));
        vinkit.add(new ArtikkeliVinkki(new URL("https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram"), "Internet celebrity cat Lil Bub has died", ""));
        vinkit.add(new YoutubeVinkki(new URL("https://www.youtube.com/watch?v=9TycLR0TqFA"), "Introduction to Scrum - 7 Minutes", ""));
        vinkr = mock(Vinkr.class);
        when(vinkr.getVinkit()).thenReturn(vinkit);
        validoija = mock(Validoija.class);
        when(validoija.validoiOtsikko(anyString())).thenReturn(true);
        when(validoija.validoiTekija(anyString())).thenReturn(true);
        when(validoija.validoiIsbn(anyString())).thenReturn(true);
        ui = new TextUI(vinkr, input, uiOutput);
    }
    
    @Test
    public void lisaaKomentoLisaaKirjanIlmanKustannustietoja() {
        uiInput.println("lisaa");
        uiInput.println("kirja");
        uiInput.println("A Discipline of Programming");
        uiInput.println("Dijkstra, Edsger");
        uiInput.println("013215871X");
        uiInput.println("");
        uiInput.println("");
        uiInput.println("");
        uiInput.println("lopeta");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }
    
    @Test
    public void lisaaKomentoLisaaKirjanKustannustiedoilla() {
        uiInput.println("lisaa");
        uiInput.println("kirja");
        uiInput.println("A Discipline of Programming");
        uiInput.println("Dijkstra, Edsger");
        uiInput.println("013215871X");
        uiInput.println("Upper Saddle River");
        uiInput.println("Prentice Hall");
        uiInput.println("1997");
        uiInput.println("lopeta");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }
    
    @Test
    public void lisaaKomentoLisaaArtikkelin() {
        uiInput.println("lisaa");
        uiInput.println("artikkeli");
        uiInput.println("https://www.theverge.com/2019/12/2/20992023/lil-bub-cat-dead-viral-internet-celebrity-animal-welfare-instagram");
        uiInput.println("Internet celebrity cat Lil Bub has died");
        uiInput.println("Lee, Dami");
        uiInput.println("The Verge");
        uiInput.println("02.12.2019");
        uiInput.println("lopeta");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }
    
    @Test
    public void lisaaKomentoLisaaYoutubeVideon() {
        uiInput.println("lisaa");
        uiInput.println("youtube");
        uiInput.println("https://www.youtube.com/watch?v=9TycLR0TqFA");
        uiInput.println("Introduction to Scrum - 7 Minutes");
        uiInput.println("Uzility");
        uiInput.println("26.07.2014");
        uiInput.println("lopeta");
        ui.run();
        verify(vinkr).lisaaVinkki(any());
    }
    
    @Test
    public void listaaKomentoTulostaaVinkit() {
        uiInput.println("listaa");
        uiInput.println("lopeta");
        ui.run();
        
        String output = getOutput();
        for (Vinkki vinkki : vinkit) {
            assertTrue(output.contains(vinkki.tulosta()));
        }
    }

    @Test
    public void apuaKomentoListaaKomennot() {
        uiInput.println("apua");
        uiInput.println("lopeta");
        ui.run();

        String output = getOutput();
        assertTrue(output.contains("apua"));
        assertTrue(output.contains("lisaa"));
        assertTrue(output.contains("listaa"));
        assertTrue(output.contains("lopeta"));
    }
    
    private String getOutput() {
        return new String(uiOutput.toByteArray());
    }
}
