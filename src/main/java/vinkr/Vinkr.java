package vinkr;

import java.util.ArrayList;
import java.util.List;

public class Vinkr {

    private List<KirjaVinkki> vinkit = new ArrayList<KirjaVinkki>();

    public void lisaaKirja(KirjaVinkki kirja) {
        vinkit.add(kirja);
    }

    public List<KirjaVinkki> getKirjat() {
        return vinkit;
    }

}
