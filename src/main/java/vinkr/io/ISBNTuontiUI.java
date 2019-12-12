package vinkr.io;

import java.io.PrintStream;
import java.util.Scanner;

import vinkr.App;
import vinkr.Validoija;
import vinkr.Varit;

public class ISBNTuontiUI {
    private ISBNTuonti tuonti;
    private Scanner input;
    private PrintStream output;
    private Validoija validoija;
    
    
    public ISBNTuontiUI(ISBNTuonti tuonti, Validoija validoija) {
        this.tuonti = tuonti;
        this.input = new Scanner(App.INPUT); 
        this.output = new PrintStream(App.OUTPUT);
        this.validoija = validoija;
    }

    
    public String korjaaKirjoittaja(String kirjoittaja) {
        while (true) {
            output.print("Korjaa nimi '");
            output.print(Varit.varjaa(Varit.SININEN, kirjoittaja));
            output.print("'  muotoon 'Sukunimi, Etunimi': ");
            String korjattu = input.nextLine();
            if (validoija.validoiTekija(korjattu) == true) {
                return korjattu;
            } else {
                output.println("Virhe: Kirjoittajan nimi ei ole muotoa 'Sukunimi, Etunimi'");
            }
        }
    }
    
    
}
