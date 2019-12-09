package vinkr;

public final class Varit {

    public static final String NOLLAA = "\u001b[0m";

    public static final String PUNAINEN = "\u001b[31m";
    public static final String KELTAINEN = "\u001B[33m";
    public static final String VIHREA = "\u001B[32m";
    public static final String SININEN = "\u001B[34m";
    public static final String SINIVIHREA = "\u001B[36m";
    public static final String VIOLETTI = "\u001B[95m";

    public static String varjaa(String vari, String tuloste) {
        return vari + tuloste + Varit.NOLLAA;
    }
}
