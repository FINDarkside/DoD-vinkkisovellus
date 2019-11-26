package vinkr;

import vinkr.vinkit.KirjaVinkki;
import vinkr.vinkit.Vinkki;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUI {

    private final Vinkr app;
    private final Scanner input;
    private final PrintStream output;

    public TextUI(Vinkr app, InputStream inputStream, OutputStream outputStream) {
        this.app = app;
        this.input = new Scanner(inputStream);
        this.output = new PrintStream(outputStream);
    }

    public void run() {
        tulostaKomennot();

        while (true) {
            String komento = getInput("Komento");
            if (komento.equals("lopeta")) {
                break;
            }
            kasitteleKomento(komento);
        }
    }

    private void tulostaKomennot() {
        output.println("Komennot:");
        output.println("lisaa: Lisää kirja");
        output.println("listaa: Listaa kaikki lukuvinkit");
        output.println("lopeta: Sulkee sovelluksen");
    }

    private void kasitteleKomento(String komento) {
        switch (komento) {
            case "lisaa":
                lisaaKirja();
                break;
            case "listaa":
                listaaVinkit();
                break;
            default:
                output.println("Komentoa ei tunnistettu");
        }
    }

    private void lisaaKirja() {
        String kirjoittaja = getInput("Kirjoittaja");
        String otsikko = getInput("Otsikko");
        String isbn = getInput("ISBN");
        KirjaVinkki kirjaVinkki = new KirjaVinkki(otsikko);
        kirjaVinkki.setTekija(kirjoittaja);
        kirjaVinkki.setISBN(isbn);
        app.lisaaVinkki(kirjaVinkki);
        output.println("Kirja lisätty");
    }

    private void listaaVinkit() {
        System.out.println();
        for (Vinkki vinkki : app.getVinkit()) {
            System.out.println(vinkki.tulosta());
        }
    }

    private String getInput(String name) {
        output.print(name + ": ");
        return input.nextLine();
    }

}
