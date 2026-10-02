public class Song {
    private String nazev;
    private String interpret;
    private int delkaS;
    private int pocetPrehrani;

    public Song(String nazev, String interpret, int delkaS) {
        this.nazev = nazev;
        this.interpret = interpret;
        this.delkaS = delkaS;
        this.pocetPrehrani = 0;
    }

    public String getNazev() { return nazev; }
    public void setNazev(String nazev) { this.nazev = nazev; }

    public String getInterpret() { return interpret; }
    public void setInterpret(String interpret) { this.interpret = interpret; }

    public int getDelkaS() { return delkaS; }
    public void setDelkaS(int delkaS) { this.delkaS = delkaS; }

    public int getPocetPrehrani() { return pocetPrehrani; }
    public void incrementPocetPrehrani() { this.pocetPrehrani++; }

    @Override
    public String toString() {
        return nazev + " - " + interpret + " [" + delkaS + "s]";
    }
}
