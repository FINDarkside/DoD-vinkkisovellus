package vinkr.vinkit;

public interface Vinkki {
	public String getID();
	public String getOtsikko();
	public String getTyyppi();
	public void setOtsikko(String otsikko);
	public void setTyyppi(String tyyppi);
	public String tulosta();
	public String toString();
}
