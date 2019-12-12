package vinkr.IO;

import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import vinkr.App;
import vinkr.TextUI;
import vinkr.Validoija;
import vinkr.vinkit.KirjaVinkki;

public class ISBNTuonti {

    private static String GBBASEURL = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private static String OLBASEURL = "https://openlibrary.org/api/books?bibkeys=ISBN:";
    private static String OLSUFFIX = "&jscmd=data&format=json";
    private Validoija validoija;
    private ISBNTuontiUI tuontiUI;
    private String isbn;
    private String siivottuIsbn;
    private JSONObject kirjanTiedot;
    
    public ISBNTuonti(String isbn, Validoija validoija) throws IOException, IllegalArgumentException {
        this.validoija = validoija;
        this.tuontiUI = new ISBNTuontiUI(this, validoija);
        this.isbn = isbn;
        this.siivottuIsbn = isbn.replaceAll("[\\-\\s]", "");
        haeKirjanTiedot();
    }
    
    public KirjaVinkki luoKirjaVinkki() {
        String otsikko = getOtsikko();
        ArrayList<String> kirjoittajat = getKirjoittajat();
        KirjaVinkki kirjaVinkki = new KirjaVinkki(otsikko, kirjoittajat, this.isbn); 
        kirjaVinkki.setJulkaisupaikka(getJulkaisupaikka());
        kirjaVinkki.setKustantaja(getKustantaja());
        kirjaVinkki.setJulkaisuvuosi(getJulkaisuvuosi());
        return kirjaVinkki;
    }

    public String getOtsikko() {
        return this.kirjanTiedot.optString("title");
    }
    
    public ArrayList<String> getKirjoittajat() {
        ArrayList<String> kirjoittajat = new ArrayList<>();
        JSONArray lista = this.kirjanTiedot.getJSONArray("authors");
        for (int i = 0; i < lista.length(); i++) {
            String nimi = lista.getJSONObject(i).optString("name");
            try {
                nimi = muotoileKirjoittaja(nimi);
            } catch (IOException e) {
                nimi = this.tuontiUI.korjaaKirjoittaja(nimi);
                kirjoittajat.add(nimi);
            }
            if (validoija.validoiTekija(nimi) == true) {
                kirjoittajat.add(nimi);
            } else {
                nimi = this.tuontiUI.korjaaKirjoittaja(nimi);
                kirjoittajat.add(nimi);
            }
        }
        return kirjoittajat;
    }
    
    public String getJulkaisupaikka() {
        return haeListaTekstiksi("publish_places");
    }
    
    public String getKustantaja() {
        return haeListaTekstiksi("publishers");
    }
    
    public int getJulkaisuvuosi() {
        String pvm = kirjanTiedot.optString("publish_date");
        Pattern kaava = Pattern.compile("[0-9]{4}");
        Matcher testaaja = kaava.matcher(pvm);
        if (validoija.validoiVuosi(pvm) == true) {
            return (int) Integer.parseInt(pvm);
        } else if (testaaja.find()) {
            String vuosi = testaaja.group(1);
            if (validoija.validoiVuosi(vuosi) == true) {
                return (int) Integer.parseInt(vuosi);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    // Apumetodit
    public JSONObject haeKirjanTiedot() throws IOException {
        String url = OLBASEURL + this.siivottuIsbn + OLSUFFIX;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet pyynto = new HttpGet(url);
        pyynto.addHeader("accept", "application/json");
        HttpResponse vastaus = client.execute(pyynto);
        String json = IOUtils.toString(vastaus.getEntity().getContent(), "UTF-8");        
        if (json.equals("{}")) {
            return new JSONObject();
        } else {
            JSONObject object = new JSONObject(json).getJSONObject("ISBN:" + this.siivottuIsbn);
            return object;
        }
    }
    
    private String muotoileKirjoittaja(String kirjoittaja) throws IOException {
        String viafID;
        try {
            viafID = haeViafId(kirjoittaja.replace(" ", "+").toLowerCase());
            PrintStream output = new PrintStream(App.OUTPUT);
        } catch (IOException e) {
            return kirjoittaja;
        }
        String url = "http://www.viaf.org/viaf/" + viafID + "/viaf.json";
        if (haeViafNimi(url) == null) {
            return kirjoittaja;
        } else {
            return haeViafNimi(url);
        }
    }
    
    public String haeViafId(String hakutermi) throws IOException {
        String url = "http://www.viaf.org/viaf/AutoSuggest?query=" + hakutermi;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet pyynto = new HttpGet(url);
        pyynto.addHeader("accept", "application/json");
        HttpResponse vastaus = client.execute(pyynto);
        String json = IOUtils.toString(vastaus.getEntity().getContent(), "UTF-8");        
        String viafId = new JSONObject(json).getJSONArray("result").getJSONObject(0).getString("viafid");
        return viafId;
    }
    
    public String haeViafNimi(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet pyynto = new HttpGet(url);
        pyynto.addHeader("accept", "application/json");
        HttpResponse vastaus = client.execute(pyynto);
        String json = IOUtils.toString(vastaus.getEntity().getContent(), "UTF-8");        
        JSONObject object = new JSONObject(json);
        for (int i = 0; i < object.getJSONObject("mainHeadings").getJSONArray("data").length(); i++) {
            if (validoija.validoiTekija(object.getJSONObject("mainHeadings").getJSONArray("data").getJSONObject(i).getString("text")) == true) {
                return object.getJSONObject("mainHeadings").getJSONArray("data").getJSONObject(i).getString("text"); 
            } 
        }
        return null;
    }
    
    private String haeListaTekstiksi(String avain) {
        JSONArray lista = this.kirjanTiedot.optJSONArray(avain);
        if (lista == null || lista.isEmpty()) {
            return "";
        } else {
            String teksti = lista.getJSONObject(0).optString("name");
            if (lista.length() > 1) {
                for (int i = 1; i < lista.length() - 1; i++) {
                    teksti += ", " + lista.getJSONObject(i).optString("name");
                }
                teksti += " & " + lista.getJSONObject(lista.length() - 1).optString("name");
            }
            return teksti;
        }
    }
    
}
