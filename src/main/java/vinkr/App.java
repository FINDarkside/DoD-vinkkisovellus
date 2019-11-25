package vinkr;

public class App {

    public static void main(String[] args) {
        Vinkr app = new Vinkr();
        TextUI ui = new TextUI(app, System.in, System.out);
        ui.run();
    }
}
