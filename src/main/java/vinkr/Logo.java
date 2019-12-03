
package vinkr;


public class Logo {
    String[] sisalto;
    
    public Logo() {
        this.sisalto = new String[7]; 
        this.sisalto[0] = "       _       _";
        this.sisalto[1] = "      (_)     | |"; 
        this.sisalto[2] = "__   ___ _ __ | | ___ __"; 
        this.sisalto[3] = "\\ \\ / / | '_ \\| |/ / '__|"; 
        this.sisalto[4] = " \\ V /| | | | |   <| |"; 
        this.sisalto[5] = "  \\_/ |_|_| |_|_|\\_\\_|";
        this.sisalto[6] = "";
    }
    
    public String logoStringiksi() {
        String logoStringina = "";
        
        for (int i = 0; i < sisalto.length; i++) {
            logoStringina += sisalto[i];
            if (i != sisalto.length-1) {
                logoStringina += "\n";
            }
        }
        
        return logoStringina;
    }
}
