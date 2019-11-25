package vinkr;

import java.util.ArrayList;
import java.util.List;
import vinkr.vinkit.Vinkki;

public class Vinkr {

    private List<Vinkki> vinkit = new ArrayList<Vinkki>();

    public void lisaaVinkki(Vinkki vinkki) {
        vinkit.add(vinkki);
    }

    public List<Vinkki> getVinkit() {
        return vinkit;
    }

}
