package vinkr.io;

import java.io.PrintStream;
import java.util.Scanner;

import vinkr.App;
import vinkr.TextUI;
import vinkr.Validoija;
import vinkr.Varit;

public class ISBNTuontiUI {

    private ISBNTuonti tuonti;
    private TextUI textUi;
    private PrintStream output;
    private Validoija validoija;

    public ISBNTuontiUI(ISBNTuonti tuonti, Validoija validoija, TextUI textUi) {
        this.tuonti = tuonti;
        this.validoija = validoija;
        this.textUi = textUi;
    }

    public String korjaaKirjoittaja(String kirjoittaja) {
        while (true) {
            String s = "Korjaa nimi '";
            s += Varit.varjaa(Varit.SININEN, kirjoittaja);
            s += "'  muotoon 'Sukunimi, Etunimi'";
            String korjattu = textUi.getInput(s);
            if (validoija.validoiTekija(korjattu) == true) {
                return korjattu;
            } else {
                output.println("Virhe: Kirjoittajan nimi ei ole muotoa 'Sukunimi, Etunimi'");
            }
        }
    }

}
