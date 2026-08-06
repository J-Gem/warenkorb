package warenkorb;

public class Artikel {

    private int artikelnummer;
    private String beschreibung;
    private float nettopreis;
    private float steuersatz;

    public Artikel(int artikelnummer, String beschreibung, float nettopreis, float steuersatz) {
        this.artikelnummer = artikelnummer;
        this.beschreibung = beschreibung;
        this.nettopreis = nettopreis;
        this.steuersatz = steuersatz;
    }

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setNettopreis(float nettopreis) {
        this.nettopreis = nettopreis;
    }

    public void setSteuersatz(float steuersatz) {
        this.steuersatz = steuersatz;
    }

    public int getArtikelnummer() {
        return this.artikelnummer;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public float getNettopreis() {
        return this.nettopreis;
    }

    public float getSteuersatz() {
        return this.steuersatz;
    }
}