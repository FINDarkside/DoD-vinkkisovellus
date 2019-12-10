package vinkr.IO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ISBNTuonti {

    private static String BASEURL = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
    private String siivottuIsbn;
    
    
    public ISBNTuonti(String isbn) throws IOException, IllegalArgumentException {
        this.siivottuIsbn = isbn.replaceAll("[\\-\\s]", "");
            JSONArray kirjanTiedot = haeKirjanTiedot();
        
        
        
        
    }
    
    
    

    
    private JSONArray haeKirjanTiedot() throws Exception {
        String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + this.siivottuIsbn;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet pyynto = new HttpGet(url);
        pyynto.addHeader("accept", "application/json");
        HttpResponse vastaus = client.execute(pyynto);
        String json = IOUtils.toString(vastaus.getEntity().getContent());        
        JSONArray array = new JSONArray(json);
        return array;
    }
        

    
}
