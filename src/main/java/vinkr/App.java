package vinkr;

import com.google.gson.JsonParseException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws URISyntaxException {
        String currentFolder = new File(TextUI.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
        Path savePath = Paths.get(currentFolder, "vinkit.json");
        Vinkr app = new Vinkr();
        if (Files.exists(savePath)) {
            String json;
            try {
                json = Files.readString(savePath);
                app = Vinkr.deserialisoi(json);
            } catch (IOException | JsonParseException ex) {
                System.out.println("Vinkkien lataus tiedostosta " + savePath + " epäonnistui.");
                return;
            }
        } else {
            System.out.println(savePath + " doesn't exist");
        }
        TextUI ui = new TextUI(app, System.in, System.out, savePath);
        ui.run();
    }
}
