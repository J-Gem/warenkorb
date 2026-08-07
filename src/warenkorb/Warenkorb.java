package warenkorb;

import java.util.List;
import java.util.ArrayList;

public class Warenkorb {

    private List<Artikel> artikelListe = new ArrayList<>();
    private String name;

    public Warenkorb(String name) {
        this.name = name;
    }

    public void ausgabe() {
        for (int i = 0; i < artikelListe.lastIndexOf(artikelListe); i++)
            System.out.println("Artikelnummer: " + artikelListe.get(i).getArtikelnummer() + ", Beschreibung:"
                    + artikelListe.get(i).getBeschreibung() + ", Nettopreis: " + artikelListe.get(i).getNettopreis()
                    + ", Steuersatz: " + artikelListe.get(i).getSteuersatz());
    }

    public boolean artikelHinzufuegen(int artikelnummer, String beschreibung, float nettopreis, float steuersatz) {
        Artikel artiekl = new Artikel(artikelnummer, beschreibung, nettopreis, steuersatz);
        artikelListe.add(artiekl);
        return true;
    }

    public boolean artikelLoeschen(int artikelnummer) {
        artikelListe.remove(artikelnummer);
        return true;
    }

    public float gesamtpreisBerechnen() {
        float gesamtpreis = 0;
        for (int i = 0; i < artikelListe.lastIndexOf(artikelListe); i++) {
            gesamtpreis += (artikelListe.get(i).getNettopreis()
                    + artikelListe.get(i).getNettopreis() * artikelListe.get(i).getSteuersatz());
        }
        return gesamtpreis;
    }

    public int gesamtAnzahlBerechnen() {
        return artikelListe.lastIndexOf(artikelListe);
    }

    public Artikel getArtikel(int index){
        return artikelListe.get(index);
    }

    public List<Artikel> getArtikelListe(){
        return artikelListe;
    }

    public String getName(){
        return this.name;
    }
}
