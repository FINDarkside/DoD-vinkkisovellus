package vinkr;

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
        this.output.println("Komennot");
        this.output.println("lisaa: Lisää kirja");
        while (true) {
            String komento = getInput("Komento");
            if (komento.equals("add")) {
                lisaaKirja();
            }
        }
    }

    private void lisaaKirja() {
        String kirjoittaja = getInput("Kirjoittaja");
        String otsikko = getInput("Otsikko");
        String isbn = getInput("ISBN");
        KirjaVinkki kirjaVinkki = new KirjaVinkki(kirjoittaja, otsikko, isbn);
        app.lisaaKirja(kirjaVinkki);
        output.println("Kirja lisätty");
    }

    private String getInput(String name) {
        output.print(name + ": ");
        return input.nextLine();
    }

}
