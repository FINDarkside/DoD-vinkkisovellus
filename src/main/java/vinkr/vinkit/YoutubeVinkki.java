package vinkr.vinkit;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YoutubeVinkki implements Vinkki {
    private String id;
    private String otsikko;
    private String tyyppi = "youtube";
    private String kanava;
    private Date julkaisupvm = null;
    private URL url; 
    
    public YoutubeVinkki(URL url, String otsikko, String kanava) {
        this.id = luoID();
        this.otsikko = otsikko;
        this.kanava = kanava;
        this.url = url;
    }
    
 // Getterit
    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getOtsikko() {
        return this.otsikko;
    }

    @Override
    public String getTyyppi() {
        return this.tyyppi;
    }

    public String getKanava() {
        return this.kanava;
    }
    
    public Date getJulkaisupvm() {
        return this.julkaisupvm;
    }
    
    public URL getUrl() {
        return this.url;
    }
    
 // Setterit
    @Override
    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void setKanava(String kanava) {
        this.kanava = kanava;
    }

    public void setJulkaisupvm(Date julkaisupvm) {
        this.julkaisupvm = julkaisupvm;
    }
    
    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public String tulosta() {
        // TODO Auto-generated method stub
        return null;
    }

    
    // Apumetodit
    private String luoID() {
        Date nykyhetki = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String id = ft.format(nykyhetki);
        return id;
    }
    
}
