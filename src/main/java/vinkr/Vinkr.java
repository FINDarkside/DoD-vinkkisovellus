package vinkr;

import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import vinkr.vinkit.*;

public class Vinkr {

    private static final Gson gson = new Gson();

    private List<Vinkki> vinkit = new ArrayList<Vinkki>();

    public static Vinkr deserialisoi(String json) {
        TallennusData tallennus = gson.fromJson(json, TallennusData.class);
        Vinkr vinkr = new Vinkr();
        vinkr.vinkit.addAll(tallennus.artikkelit);
        vinkr.vinkit.addAll(tallennus.kirjat);
        vinkr.vinkit.addAll(tallennus.youtube);
        return vinkr;
    }

    public void lisaaVinkki(Vinkki vinkki) {
        vinkit.add(vinkki);
    }

    public List<Vinkki> getVinkit() {
        return vinkit;
    }

    public String serialisoi() {
        TallennusData data = new TallennusData();
        for (Vinkki vinkki : vinkit) {
            if (vinkki.getClass() == ArtikkeliVinkki.class) {
                data.artikkelit.add((ArtikkeliVinkki) vinkki);
            } else if (vinkki.getClass() == KirjaVinkki.class) {
                data.kirjat.add((KirjaVinkki) vinkki);
            } else if (vinkki.getClass() == YoutubeVinkki.class) {
                data.youtube.add((YoutubeVinkki) vinkki);
            } else {
                throw new RuntimeException("Tuntematon Vinkki luokka");
            }
        }
        return gson.toJson(data);
    }

}
