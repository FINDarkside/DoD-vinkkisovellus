package vinkr;

import com.google.gson.JsonParseException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static InputStream INPUT = System.in;
    public static OutputStream OUTPUT = System.out;
    
    public static void main(String[] args) throws URISyntaxException {
        String currentFolder = new File(TextUI.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        Path savePath = Paths.get(currentFolder, "vinkit.json");
        Tallennus tallennus = new Tallennus(savePath);
        Vinkr app = new Vinkr();
        if (Files.exists(savePath)) {
            String json;
            try {
                json = tallennus.lataa();
                app = Vinkr.deserialisoi(json);
            } catch (IOException | JsonParseException ex) {
                System.out.println("Vinkkien lataus tiedostosta " + savePath + " epäonnistui.");
                return;
            }
        } else {
            System.out.println(savePath + " doesn't exist");
        }
        TextUI ui = new TextUI(app, INPUT, OUTPUT, tallennus);
        ui.run();
    }
}
