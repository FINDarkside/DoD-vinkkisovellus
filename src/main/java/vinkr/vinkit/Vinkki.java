package vinkr.vinkit;

public interface Vinkki {
    public String getID();
    public String getOtsikko();
    public String getTyyppi();
    public String getVari();
    public void setOtsikko(String otsikko);
    public String tulosta();
    public String toString();
    public void avaaLinkki() throws Exception;
}
